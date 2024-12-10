/**
 * copyright (C), 2015-2024
 * fileName: CodeSandboxProxy
 *
 * @author: mlt
 * date:    2024/11/22 下午5:04
 * description: 代码沙箱代理
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/11/22 下午5:04           V1.0        代码沙箱代理
 */
package com.yupi.yuojbackendjudgeservice.codeSandbox;

import com.yupi.yuojbackendmodel.model.codesandbox.ExcuteReponse;
import com.yupi.yuojbackendmodel.model.codesandbox.ExcuteRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 代码沙箱代理
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/11/22
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandbox {

    private final CodeSandbox codeSandbox;
    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExcuteReponse excuteCode(ExcuteRequest request) {
        log.info(request.toString());
        ExcuteReponse excuteReponse = codeSandbox.excuteCode(request);
        log.info(excuteReponse.toString());
        return excuteReponse;
    }
}
