package com.devsuperior.hroauth.services;

import com.devsuperior.hroauth.dto.UserDTO;
import com.devsuperior.hroauth.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserFeignClient userFeignClient;

    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public UserDTO findUserByEmail(final String email) {
        UserDTO user = this.userFeignClient.nomeDoMetodoNaoImporta(email).getBody();

        if (Objects.isNull(user))
            throw new IllegalArgumentException("usuario não encontrado");

        LOGGER.info("usuario encontrado com email:".concat(email));
        return user;
    }

}
