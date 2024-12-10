/**
 * copyright (C), 2015-2024
 * fileName: RemoteCodeSandbox
 *
 * @author: mlt
 * date:    2024/11/22 下午4:30
 * description: 远程codeSandbox
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/11/22 下午4:30           V1.0        远程codeSandbox
 */
package com.yupi.yuojbackendjudgeservice.codeSandbox.impl;

import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.yupi.yuojbackendcommon.common.ErrorCode;
import com.yupi.yuojbackendcommon.exception.BusinessException;
import com.yupi.yuojbackendjudgeservice.codeSandbox.CodeSandbox;
import com.yupi.yuojbackendmodel.model.codesandbox.ExcuteReponse;
import com.yupi.yuojbackendmodel.model.codesandbox.ExcuteRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 远程codeSandbox
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/11/22
 */
public class RemoteCodeSandbox implements CodeSandbox {
    private final String AUTH_KEY="key";
    private final String AUTH_SECRET="secret";
    @Override
    public ExcuteReponse excuteCode(ExcuteRequest request) {
        Map<String,String> headers=new HashMap<>();
        Gson gson=new Gson();
        String json=gson.toJson(request);
        headers.put(AUTH_KEY,AUTH_SECRET);
        String responseStr = HttpUtil.createPost("localhost:8090/execute")
                .header(AUTH_KEY,AUTH_SECRET)
                .body(json)
                .execute()
                .body();
        if(responseStr==null){
            throw new BusinessException(ErrorCode.REMOTE_EXECUTE_ERROR,"远程调用失败");
        }
        return gson.fromJson(responseStr, ExcuteReponse.class);
    }
}
