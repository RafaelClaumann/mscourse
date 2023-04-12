package com.devsuperior.hroauth.resources;

import com.devsuperior.hroauth.dto.UserDTO;
import com.devsuperior.hroauth.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserEndpoint {

    final UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/search")
    public ResponseEntity<UserDTO> test(@RequestParam(name = "email") final String email) {
        try {
            final UserDTO user = this.userService.findUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
