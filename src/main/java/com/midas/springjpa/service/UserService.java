package com.midas.springjpa.service;

import com.midas.springjpa.domain.auth.User;
import com.midas.springjpa.domain.auth.UserRepository;
import com.midas.springjpa.exception.auth.UserNotFoundException;
import com.midas.springjpa.web.dto.UserResponseDto;
import com.midas.springjpa.web.dto.UserSaveRequestDto;
import com.midas.springjpa.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserSaveRequestDto requestDto) {
        User savedUser = userRepository.save(requestDto.toEntity());
        return savedUser.getId();
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다. id=" + id));

        user.update(requestDto.getName(), requestDto.getPassword());
        return id;
    }

    @Transactional
    public Long delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다. id=" + id));
        userRepository.delete(user);
        return id;
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다. id=" + id));
        return new UserResponseDto(user);
    }
}
