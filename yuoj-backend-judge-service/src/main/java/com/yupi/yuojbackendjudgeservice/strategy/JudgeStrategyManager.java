/**
 * copyright (C), 2015-2024
 * fileName: JudgeStrategyManager
 *
 * @author: mlt
 * date:    2024/11/22 下午7:28
 * description:
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/11/22 下午7:28           V1.0
 */
package com.yupi.yuojbackendjudgeservice.strategy;

import com.yupi.yuojbackendmodel.model.codesandbox.JudgeInfo;

/**
 *
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/11/22
 */
public class JudgeStrategyManager {

    public static JudgeInfo doJudge(JudgeContext judgeContext) {
        String language = judgeContext.getQuestionSubmit().getLanguage();
        JudgeStrategy judgeStrategy=null;
        switch (language.toLowerCase()) {
            case "java":
                judgeStrategy= new JavaJudgeStrategy();
                return judgeStrategy.doJudge(judgeContext);
            default:
                judgeStrategy= new JavaJudgeStrategy();
                return judgeStrategy.doJudge(judgeContext);
        }

    }
}
