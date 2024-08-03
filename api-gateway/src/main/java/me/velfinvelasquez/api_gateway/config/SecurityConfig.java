package me.velfinvelasquez.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import reactor.core.publisher.Mono;

@Configuration
public class SecurityConfig {

    @SuppressWarnings("removal")
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration corsConfig = new CorsConfiguration();
                    corsConfig.addAllowedOrigin("*");
                    corsConfig.addAllowedOrigin("http://localhost:5173");
                    corsConfig.addAllowedMethod("*");
                    corsConfig.addAllowedHeader("*");
                    corsConfig.setAllowCredentials(true);
                    return corsConfig;
                }))
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/actuator/**").permitAll())
                .authorizeExchange(auth -> {
                    auth.pathMatchers("/actuator/**").permitAll();
                    auth.anyExchange().authenticated();
                })
                .oauth2ResourceServer(server -> server.jwt().jwtAuthenticationConverter(grantedAuthorities()));

        return http.build();
    }

    Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthorities() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new RoleKeycloakConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}

