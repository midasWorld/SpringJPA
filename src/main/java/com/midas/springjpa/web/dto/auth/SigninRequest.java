package com.midas.springjpa.web.dto.auth;

import lombok.Data;

@Data
public class SigninRequest {
    private String email;
    private String password;
}
