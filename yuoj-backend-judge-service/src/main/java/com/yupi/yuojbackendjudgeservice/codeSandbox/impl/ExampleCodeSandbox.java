/**
 * copyright (C), 2015-2024
 * fileName: ExampleCodeSandbox
 *
 * @author: mlt
 * date:    2024/11/22 下午4:29
 * description: dump codeSandbox
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/11/22 下午4:29           V1.0        dump codeSandbox
 */
package com.yupi.yuojbackendjudgeservice.codeSandbox.impl;

import com.yupi.yuojbackendjudgeservice.codeSandbox.CodeSandbox;
import com.yupi.yuojbackendmodel.model.codesandbox.ExcuteReponse;
import com.yupi.yuojbackendmodel.model.codesandbox.ExcuteRequest;
import com.yupi.yuojbackendmodel.model.codesandbox.JudgeInfo;
import com.yupi.yuojbackendmodel.model.enums.JudgeInfoMessageEnum;
import com.yupi.yuojbackendmodel.model.enums.QuestionSubmitStatusEnum;

/**
 * dump codeSandbox
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/11/22
 */
public class ExampleCodeSandbox implements CodeSandbox {

    @Override
    public ExcuteReponse excuteCode(ExcuteRequest request) {
        JudgeInfo judgeInfo = JudgeInfo.builder()
                .message(JudgeInfoMessageEnum.ACCEPTED.getText())
                .time(100L)
                .memory(100L)
                .build();
        return ExcuteReponse.builder()
                .outputList(request.getInputList())
                .status(QuestionSubmitStatusEnum.SUCCEED.getValue())
                .judgeInfo(judgeInfo)
                .message("success ")
                .language("c++")
                .build();
    }
}
