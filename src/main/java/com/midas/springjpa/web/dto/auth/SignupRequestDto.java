package com.midas.springjpa.web.dto.auth;

import com.midas.springjpa.domain.auth.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    private String email;
    private String name;
    private String password;

    @Builder
    public SignupRequestDto(String email, String name, String password) {
        this.email = email;
        this.password = password;
    }

    public UserEntity toEntity(String encryptedPwd) {
        return UserEntity.builder()
                .email(email)
                .name(name)
                .encryptedPwd(encryptedPwd)
                .build();
    }
}
