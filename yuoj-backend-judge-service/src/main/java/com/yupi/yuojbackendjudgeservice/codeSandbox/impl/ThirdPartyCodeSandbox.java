/**
 * copyright (C), 2015-2024
 * fileName: ThirdPartyCodeSandbox
 *
 * @author: mlt
 * date:    2024/11/22 下午4:31
 * description:
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/11/22 下午4:31           V1.0
 */
package com.yupi.yuojbackendjudgeservice.codeSandbox.impl;

import com.yupi.yuojbackendjudgeservice.codeSandbox.CodeSandbox;
import com.yupi.yuojbackendmodel.model.codesandbox.ExcuteReponse;
import com.yupi.yuojbackendmodel.model.codesandbox.ExcuteRequest;

/**
 *
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/11/22
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExcuteReponse excuteCode(ExcuteRequest request) {
        System.out.println("thirdParty code sandbox");
        return null;
    }
}
