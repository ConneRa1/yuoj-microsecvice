/**
 * copyright (C), 2015-2024
 * fileName: JudgeServiceImpl
 *
 * @author: mlt
 * date:    2024/11/22 下午5:19
 * description:
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/11/22 下午5:19           V1.0
 */
package com.yupi.yuojbackendjudgeservice.impl;

import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.yupi.yuojbackendcommon.common.ErrorCode;
import com.yupi.yuojbackendcommon.exception.BusinessException;
import com.yupi.yuojbackendjudgeservice.codeSandbox.CodeSandbox;
import com.yupi.yuojbackendjudgeservice.codeSandbox.CodeSandboxFactory;
import com.yupi.yuojbackendjudgeservice.codeSandbox.CodeSandboxProxy;
import com.yupi.yuojbackendjudgeservice.strategy.JudgeContext;
import com.yupi.yuojbackendjudgeservice.strategy.JudgeStrategyManager;
import com.yupi.yuojbackendmodel.model.codesandbox.ExcuteReponse;
import com.yupi.yuojbackendmodel.model.codesandbox.ExcuteRequest;
import com.yupi.yuojbackendmodel.model.codesandbox.JudgeInfo;
import com.yupi.yuojbackendmodel.model.dto.question.JudgeCase;
import com.yupi.yuojbackendmodel.model.entity.Question;
import com.yupi.yuojbackendmodel.model.entity.QuestionSubmit;
import com.yupi.yuojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import com.yupi.yupjbackendserviceclient.service.JudgeService;
import com.yupi.yupjbackendserviceclient.service.QuestionFeignClient;
import com.yupi.yupjbackendserviceclient.service.QuestionService;
import com.yupi.yupjbackendserviceclient.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/11/22
 */
@Service
public class JudgeServiceImpl implements JudgeService {
    @Resource
    QuestionFeignClient questionFeignClient;

    @Value("${codeSandbox.type:remote}")
    private String type;

    @Override
    public QuestionSubmit judge(Long questionSubmitId) {
        //1.根据传入id判断获得题目和提交信息
        QuestionSubmit submit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (submit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        Question question = questionFeignClient.getQuestionById(submit.getQuestionId());
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        //2.如果提交状态部位等待中就不执行了
        if(!QuestionSubmitStatusEnum.WAITING.getValue().equals(submit.getStatus())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        //3.更改提交状态为执行中，防止重复
        submit.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateById(submit);
        if(!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        //4.调用沙箱后的结果
        CodeSandbox codeSandbox= CodeSandboxFactory.newInstance(type);
        codeSandbox=new CodeSandboxProxy(codeSandbox);

        List<JudgeCase> judgeCaseList = JSONUtil.toList(question.getJudgeCase(),JudgeCase.class);
        List<String> inputList=
                Optional.ofNullable(judgeCaseList).orElse(Collections.emptyList()).stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExcuteRequest request = ExcuteRequest.builder()
                .code(submit.getCode())
                .language(submit.getLanguage())
                .inputList(inputList).build();
        ExcuteReponse excuteReponse = codeSandbox.excuteCode(request);
        if(excuteReponse==null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        //5.根据沙箱结果设置返回状态
        JudgeContext judgeContext =JudgeContext.builder()
                .judgeInfo(excuteReponse.getJudgeInfo())
                .inputList(inputList)
                .outputList(excuteReponse.getOutputList())
                .judgeCaseList(judgeCaseList)
                .question(question)
                .questionSubmit(submit)
                .build();


        // manage 实现 strategy
        JudgeInfo judgeInfo = JudgeStrategyManager.doJudge(judgeContext);

        //6.更新数据库状态
        Gson gson = new Gson();
        submit.setJudgeInfo(gson.toJson(judgeInfo));
        submit.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        boolean res = questionFeignClient.updateById(submit);
        if (!res) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return submit;
    }
}