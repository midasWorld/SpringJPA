package com.midas.springjpa.web.dto;

import com.midas.springjpa.domain.users.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String email;
    private String name;
    private String password;

    @Builder
    public UserSaveRequestDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
    }
}
