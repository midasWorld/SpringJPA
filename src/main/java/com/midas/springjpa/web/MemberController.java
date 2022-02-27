package com.midas.springjpa.web;

import com.midas.springjpa.service.MemberService;
import com.midas.springjpa.web.dto.member.MemberResponseDto;
import com.midas.springjpa.web.dto.member.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/users/join")
  public ApiResponse<MemberResponseDto> join(
          @Valid @RequestBody SignUpRequestDto requestDto) {
    return ApiResponse.ok(new MemberResponseDto(
            memberService.join(requestDto.toEntity())
    ));
  }
}
