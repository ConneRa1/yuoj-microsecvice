/**
 * copyright (C), 2015-2024
 * fileName: JudgeStrategy
 *
 * @author: mlt
 * date:    2024/11/22 下午7:14
 * description:
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/11/22 下午7:14           V1.0
 */
package com.yupi.yuojbackendjudgeservice.strategy;

import com.yupi.yuojbackendmodel.model.codesandbox.JudgeInfo;

/**
 * 判题策略
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/11/22
 */
public interface JudgeStrategy {

    JudgeInfo doJudge(JudgeContext judgeContext);
}
