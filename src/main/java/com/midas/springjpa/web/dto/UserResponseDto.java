package com.midas.springjpa.web.dto;

import com.midas.springjpa.domain.users.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String email;
    private String name;
    private String password;

    public UserResponseDto(UserEntity entity) {
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.password = entity.getPassword();
    }
}
