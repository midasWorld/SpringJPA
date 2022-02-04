package com.midas.springjpa.web;

import com.midas.springjpa.service.SignupService;
import com.midas.springjpa.web.dto.auth.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserApiController {

    private final SignupService signupService;

    @PostMapping("/users")
    public ResponseEntity signup(@RequestBody SignupRequestDto requestDto) {
        signupService.createUser(requestDto);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
