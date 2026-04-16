package com.quantitymeasurement.api_gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.quantitymeasurement.api_gateway.util.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // Public APIs (login/signup/oauth2)
        if (path.contains("/login") || path.contains("/signup")
                || path.startsWith("/oauth2/") || path.startsWith("/login/oauth2/")) {
            return chain.filter(exchange);
        }

        // Authorization header check
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Extract token
        String token = authHeader.substring(7);

        // Validate token
        if (!jwtUtil.validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Extract userId from JWT and forward as header to downstream services
        Long userId = jwtUtil.extractUserId(token);
        String username = jwtUtil.extractUsername(token);

        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(r -> r.header("X-User-Id", String.valueOf(userId))
                               .header("X-Username", username))
                .build();

        return chain.filter(modifiedExchange);
    }
}