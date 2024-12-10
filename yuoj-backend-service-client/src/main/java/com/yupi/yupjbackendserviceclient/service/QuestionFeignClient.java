/**
 * copyright (C), 2015-2024
 * fileName: QuestionFeignClient
 *
 * @author: mlt
 * date:    2024/12/6 下午3:06
 * description:
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/12/6 下午3:06           V1.0
 */
package com.yupi.yupjbackendserviceclient.service;

import com.yupi.yuojbackendmodel.model.entity.Question;
import com.yupi.yuojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/12/6
 */
@FeignClient(name="yuoj-backend-question-service",path="/api/question/inner")
public interface QuestionFeignClient {

    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") Long questionId);

    @GetMapping("/question_submit/get/id")
    QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") Long questionSubmitId);

    @PostMapping("/question_submit/update")
    Boolean updateById(@RequestBody QuestionSubmit questionSubmitUpdate);
}
