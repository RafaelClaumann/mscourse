package com.devsuperior.hrapigateway;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.stream.Collectors;

final class CustomGrantedAuthoritiesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String JWT_CUSTOM_OWNER_AUTHORITIES_CLAIM_KEY = "owner_authorities";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<String> authorities = (Collection<String>) jwt.getClaims().get(JWT_CUSTOM_OWNER_AUTHORITIES_CLAIM_KEY);

        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
