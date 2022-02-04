package com.midas.springjpa.service;

import com.midas.springjpa.domain.users.UserEntity;
import com.midas.springjpa.domain.users.UserRepository;
import com.midas.springjpa.exception.users.UserNotFoundException;
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

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserResponseDto findById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 사원은 존재하지 않습니다. id=" + id));
        return new UserResponseDto(user);
    }

    @Transactional
    public Long save(UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 사원은 존재하지 않습니다. id=" + id));
        user.update(requestDto.getName(), requestDto.getPassword());
        return user.getId();
    }

    @Transactional
    public void delete(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 사원은 존재하지 않습니다. id=" + id));
        userRepository.delete(user);
    }
}
