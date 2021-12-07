package com.midas.springjpa.web;

import com.midas.springjpa.service.UserService;
import com.midas.springjpa.web.dto.UserResponseDto;
import com.midas.springjpa.web.dto.UserSaveRequestDto;
import com.midas.springjpa.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/v1/users")
    public List<UserResponseDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/v1/users/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/v1/users")
    public Long save(@RequestBody UserSaveRequestDto requestDto) {
        return userService.save(requestDto);
    }

    @PutMapping("/v1/users/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(id, requestDto);
    }

    @DeleteMapping("/v1/users/{id}")
    public Long delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
