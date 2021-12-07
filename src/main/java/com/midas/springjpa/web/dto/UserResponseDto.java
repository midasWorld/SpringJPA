package com.midas.springjpa.web.dto;

import com.midas.springjpa.domain.auth.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String password;

    public UserResponseDto(User user) {
        id = user.getId();
        email = user.getName();
        name = user.getName();
        password = user.getEncryptedPwd();
    }
}
