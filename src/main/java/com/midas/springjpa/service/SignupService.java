package com.midas.springjpa.service;

import com.midas.springjpa.domain.auth.UserEntity;
import com.midas.springjpa.domain.auth.UserRepository;
import com.midas.springjpa.exception.auth.UserNotFoundException;
import com.midas.springjpa.web.dto.auth.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SignupService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity findUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(findUser.getEmail(), findUser.getEncryptedPwd(),
                true, true, true, true,
                new ArrayList<>());
    }

    public SignupRequestDto findByEmail(String email) {
        UserEntity findUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다. email=" + email));

        return SignupRequestDto.builder()
                .email(findUser.getEmail())
                .password(findUser.getEncryptedPwd())
                .build();
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        UserEntity findUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다. id=" + id));
        userRepository.delete(findUser);
    }

    @Transactional
    public SignupRequestDto createUser(SignupRequestDto requestDto) {
        UserEntity savedUser = userRepository.save(requestDto.toEntity(passwordEncoder.encode(requestDto.getPassword())));

        return SignupRequestDto.builder()
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .password(savedUser.getEncryptedPwd())
                .build();
    }
}
