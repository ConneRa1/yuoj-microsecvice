/**
 * copyright (C), 2015-2024
 * fileName: JudgeStrategyImpl
 *
 * @author: mlt
 * date:    2024/11/22 下午7:18
 * description:
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/11/22 下午7:18           V1.0
 */
package com.yupi.yuojbackendjudgeservice.strategy;

import cn.hutool.json.JSONUtil;
import com.yupi.yuojbackendmodel.model.codesandbox.JudgeInfo;
import com.yupi.yuojbackendmodel.model.dto.question.JudgeCase;
import com.yupi.yuojbackendmodel.model.dto.question.JudgeConfig;
import com.yupi.yuojbackendmodel.model.entity.Question;
import com.yupi.yuojbackendmodel.model.enums.JudgeInfoMessageEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/11/22
 */
@Slf4j
public class JavaJudgeStrategy implements JudgeStrategy {

    /**
     *
     * @param judgeContext
     * @return
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        Question question = judgeContext.getQuestion();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());

        JudgeInfoMessageEnum judgeInfoMessage=JudgeInfoMessageEnum.WAITING;
        JudgeConfig judgeConfig = JSONUtil.toBean(question.getJudgeConfig(),JudgeConfig.class);
        if(outputList.size()!=inputList.size()){
            judgeInfoMessage=JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfo.setMessage(judgeInfoMessage.getValue());
            return judgeInfo;
        }
        //5.2 依次判断每一项输出和预期是否相等
        for(int i=0; i<judgeCaseList.size(); i++){
            log.info("ouput: "+outputList.get(i));
            log.info("judge: "+judgeCaseList.get(i).getOutput());
            if(!outputList.get(i).trim().equals(judgeCaseList.get(i).getOutput().trim())){
                judgeInfoMessage=JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfo.setMessage(judgeInfoMessage.getValue());
                return judgeInfo;
            }
        }
        //5.3 判断题目限制要求
//        if(judgeInfo.getMemory()> judgeConfig.getMemoryLimit()){
//            judgeInfoMessage=JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
//            judgeInfo.setMessage(judgeInfoMessage.getValue());
//            return judgeInfo;
//        }
        if(judgeInfo.getTime()> judgeConfig.getTimeLimit()){
            judgeInfoMessage=JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfo.setMessage(judgeInfoMessage.getValue());
            return judgeInfo;
        }
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        return judgeInfo;
    }
}
