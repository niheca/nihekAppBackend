package com.nihek.api_gateway.service;

import com.nihek.commons.jwt.JwtUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    public static class Config {}
    private final JwtUtils jwtUtils;
    private final RouterValidator validator;

    public AuthenticationFilter(JwtUtils jwtUtils, RouterValidator validator, RouterValidator routerValidator) {
        super(Config.class);
        this.jwtUtils = jwtUtils;
        this.validator = validator;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            var request = exchange.getRequest();
            ServerHttpRequest serverHttpRequest = null;

            if(validator.isSecured.test(request)) {
                if(authMissing(request)){
                    return onError(exchange,HttpStatus.UNAUTHORIZED);
                }
                String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if(token != null && token.startsWith("Bearer ")) {
                    token = token.substring(7);
                }
                else{
                    return onError(exchange,HttpStatus.UNAUTHORIZED);
                }

                serverHttpRequest = request.mutate()
                        .header("userIdRequest", jwtUtils.extractedUserId(token).toString())
                        .build();

                return chain.filter(exchange.mutate().request(serverHttpRequest).build());

            }
            else{
                return onError(exchange,HttpStatus.UNAUTHORIZED);
            }

        };
    }

    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return Mono.empty();
    }

    public boolean authMissing(org.springframework.http.server.reactive.ServerHttpRequest request) {
        return !request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

}
