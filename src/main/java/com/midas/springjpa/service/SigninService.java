package com.midas.springjpa.service;

import com.midas.springjpa.config.JWTUtil;
import com.midas.springjpa.domain.auth.UserEntity;
import com.midas.springjpa.domain.auth.UserRepository;
import com.midas.springjpa.exception.auth.UserNotFoundException;
import com.midas.springjpa.web.dto.auth.SigninRequest;
import com.midas.springjpa.web.dto.auth.SigninResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SigninService {
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    public SigninResponse signin(SigninRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다. email=" + request.getEmail()));

        return responseWithTokens(user);
    }

    private SigninResponse responseWithTokens(UserEntity user) {
        return SigninResponse.builder()
                .token(jwtUtil.createToken(user.getEmail()))
                .refreshToken(jwtUtil.createRefreshToken(user.getEmail()))
                .userName(user.getName())
                .userId(user.getId())
                .build();
    }
}
