package com.midas.springjpa.web.dto.member;

import com.midas.springjpa.domain.auth.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
  private String email;
  private String name;

  public MemberResponseDto(Member member) {
    this.email = member.getEmail();
    this.name = member.getName();
  }
}
