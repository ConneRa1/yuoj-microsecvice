/**
 * copyright (C), 2015-2024
 * fileName: JudgeInnerController
 *
 * @author: mlt
 * date:    2024/12/6 下午3:28
 * description:
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/12/6 下午3:28           V1.0
 */
package com.yupi.yuojbackendjudgeservice.controller;

import com.yupi.yuojbackendmodel.model.codesandbox.JudgeInfo;
import com.yupi.yuojbackendmodel.model.entity.QuestionSubmit;
import com.yupi.yupjbackendserviceclient.service.JudgeFeignClient;
import com.yupi.yupjbackendserviceclient.service.JudgeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/12/6
 */
@RestController
@RequestMapping("/inner")
@AllArgsConstructor
public class JudgeInnerController implements JudgeFeignClient {
    @Resource
    private final JudgeService judgeService;

    @Override
    @GetMapping("/doJudge")
    public QuestionSubmit doJudge(@RequestParam("questionSubmitId") Long questionSubmitId) {
        return judgeService.judge(questionSubmitId);
    }
}
