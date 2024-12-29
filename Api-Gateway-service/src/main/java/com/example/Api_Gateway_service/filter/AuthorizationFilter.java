package com.example.Api_Gateway_service.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    private final JwtService jwtService;

    public AuthorizationFilter(JwtService jwtService) {
        super(Config.class); // Ensure the Config class is passed to the superclass constructor
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {


            String tokenHeader = exchange.getRequest().getHeaders().getFirst("Authorization");


            if (tokenHeader ==null || !tokenHeader.startsWith("Bearer "))
            {

                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            String token = tokenHeader.substring(7);

            String userId = jwtService.getUserIdFromToken(token);


            ServerWebExchange modifiedUserID = exchange
                    .mutate()
                    .request(r -> r.header("x-User-Id", userId))
                    .build();


            return chain.filter(modifiedUserID);
        };
    }

    public static class Config {
        // Add any configuration properties if needed
    }
}
