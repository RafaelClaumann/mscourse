package com.devsuperior.hroauth.services;

import com.devsuperior.hroauth.config.SecurityUser;
import com.devsuperior.hroauth.dto.UserDTO;
import com.devsuperior.hroauth.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserFeignClient userFeignClient;

    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final UserDTO user = this.userFeignClient.nomeDoMetodoNaoImporta(email).getBody();

        if (Objects.isNull(user))
            throw new IllegalArgumentException("usuario não encontrado");

        LOGGER.info("usuario [ {} ] encontrado", user);
        return new SecurityUser(user);
    }

}
