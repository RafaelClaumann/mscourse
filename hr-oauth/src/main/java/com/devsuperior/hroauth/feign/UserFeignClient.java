package com.devsuperior.hroauth.feign;

import com.devsuperior.hroauth.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// name = spring.application.name
@FeignClient(name = "hr-user", path = "/users")
public interface UserFeignClient {

    @GetMapping(value = "/search")
    ResponseEntity<UserDTO> nomeDoMetodoNaoImporta(@RequestParam(name = "email") final String email);

}
