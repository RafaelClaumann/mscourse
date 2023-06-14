package com.devsuperior.hrapigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
public class ResourceConfig {

    private static final byte[] JWT_BYTES_SIGNING_KEY = "9faa372517ac1d389758d3750fc07acf00f542277f26fec1ce4593e93f64e338".getBytes();

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .authorizeExchange(exchangeSpec -> {
                            exchangeSpec.pathMatchers(HttpMethod.GET, "/actuator/**").permitAll();
                            exchangeSpec.anyExchange().authenticated();
                        }
                )
                .oauth2ResourceServer(resourceServer ->
                        resourceServer.jwt(jwtSpec -> {
                            jwtSpec.jwtDecoder(this.jwtDecoder());
                            jwtSpec.jwtAuthenticationConverter(this.grantedAuthoritiesExtractor());
                        })
                );

        return http.build();
    }

    public ReactiveJwtDecoder jwtDecoder() {
        SecretKey originalKey = new SecretKeySpec(JWT_BYTES_SIGNING_KEY, 0, JWT_BYTES_SIGNING_KEY.length, "RSA");
        return NimbusReactiveJwtDecoder.withSecretKey(originalKey).macAlgorithm(MacAlgorithm.HS512).build();
    }

    public Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new CustomGrantedAuthoritiesExtractor());

        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
