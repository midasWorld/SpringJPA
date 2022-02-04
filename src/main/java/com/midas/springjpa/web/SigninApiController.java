package com.midas.springjpa.web;

import com.midas.springjpa.service.SigninService;
import com.midas.springjpa.web.dto.auth.SigninRequest;
import com.midas.springjpa.web.dto.auth.SigninResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SigninApiController {
    private final SigninService signinService;

    @PostMapping("/signin")
    public ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok().body(signinService.signin(request));
    }
}
