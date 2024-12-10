/**
 * copyright (C), 2015-2024
 * fileName: GlobalAuthFilter
 *
 * @author: mlt
 * date:    2024/12/9 下午4:34
 * description:
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/12/9 下午4:34           V1.0
 */
package com.yupi.yuojbackendgateway.filter;

import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.http.HttpUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/12/9
 */
@Component
public class GlobalAuthFilter implements GlobalFilter {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String path = serverHttpRequest.getURI().getPath();
        if (antPathMatcher.match(path, "/**/inner/**")) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            DataBufferFactory bufferFactory = response.bufferFactory();
            DataBuffer dataBuffer = bufferFactory.wrap("无权限".getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }
        //TODO:可以加全局权限控制
        return chain.filter(exchange);
    }
}
