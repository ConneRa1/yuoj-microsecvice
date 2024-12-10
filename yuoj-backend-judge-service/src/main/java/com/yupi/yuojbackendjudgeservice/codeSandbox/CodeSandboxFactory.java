/**
 * copyright (C), 2015-2024
 * fileName: CodeSandboxFactory
 *
 * @author: mlt
 * date:    2024/11/22 下午4:48
 * description:
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/11/22 下午4:48           V1.0
 */
package com.yupi.yuojbackendjudgeservice.codeSandbox;

import com.yupi.yuojbackendjudgeservice.codeSandbox.impl.ExampleCodeSandbox;
import com.yupi.yuojbackendjudgeservice.codeSandbox.impl.RemoteCodeSandbox;
import com.yupi.yuojbackendjudgeservice.codeSandbox.impl.ThirdPartyCodeSandbox;

/**
 * 代码沙盒静态工厂
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/11/22
 */
public class CodeSandboxFactory {

    public static CodeSandbox newInstance(String type){
        switch (type){
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
