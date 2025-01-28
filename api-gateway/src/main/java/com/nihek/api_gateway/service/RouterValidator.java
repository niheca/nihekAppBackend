package com.nihek.api_gateway.service;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;

@Component
public class RouterValidator {
    public static List<String> openEndpoints = List.of(
            "v1/auth"
    );

    public Predicate<ServerHttpRequest> isSecured =  serverHttpRequest ->
            openEndpoints.stream().noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));

}
