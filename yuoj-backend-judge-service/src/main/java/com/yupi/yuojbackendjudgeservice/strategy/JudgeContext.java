/**
 * copyright (C), 2015-2024
 * fileName: JudgeContext
 *
 * @author: mlt
 * date:    2024/11/22 下午7:15
 * description: judege上下文
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/11/22 下午7:15           V1.0        judege上下文
 */
package com.yupi.yuojbackendjudgeservice.strategy;

import com.yupi.yuojbackendmodel.model.codesandbox.JudgeInfo;
import com.yupi.yuojbackendmodel.model.dto.question.JudgeCase;
import com.yupi.yuojbackendmodel.model.entity.Question;
import com.yupi.yuojbackendmodel.model.entity.QuestionSubmit;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * judege上下文
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/11/22
 */
@Data
@SuperBuilder
public class JudgeContext {
    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
