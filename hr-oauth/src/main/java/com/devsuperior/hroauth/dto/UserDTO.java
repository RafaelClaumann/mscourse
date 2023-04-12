package com.devsuperior.hroauth.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = {"id"})
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;

    @Setter(value = AccessLevel.NONE)
    Set<RoleDTO> roles = new HashSet<>();

}
