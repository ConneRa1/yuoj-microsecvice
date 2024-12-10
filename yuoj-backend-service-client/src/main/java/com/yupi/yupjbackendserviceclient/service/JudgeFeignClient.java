/**
 * copyright (C), 2015-2024
 * fileName: JudgeFeignClient
 *
 * @author: mlt
 * date:    2024/12/6 下午3:16
 * description:
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/12/6 下午3:16           V1.0
 */
package com.yupi.yupjbackendserviceclient.service;

import com.yupi.yuojbackendmodel.model.codesandbox.JudgeInfo;
import com.yupi.yuojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/12/6
 */
@FeignClient(name="yuoj-backend-judge-service",path = "/api/judge/inner")
public interface JudgeFeignClient {
    @GetMapping("/doJudge")
    QuestionSubmit doJudge(@RequestParam("questionSubmitId") Long questionSubmitId);
}
