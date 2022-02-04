package com.midas.springjpa.web.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SigninResponse {
    private String token;
    private String refreshToken;
    private String userName;
    private Long userId;

    @Builder
    public SigninResponse(String token, String refreshToken, String userName, Long userId) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.userName = userName;
        this.userId = userId;
    }
}
