package com.midas.springjpa.web.dto.member;

import com.midas.springjpa.domain.auth.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

  private String email;

  private String name;

  private String password;

  @Builder
  public SignUpRequestDto(String email, String name, String password) {
    this.email = email;
    this.name = name;
    this.password = password;
  }

  public Member toEntity() {
    return Member.builder()
            .email(email)
            .name(name)
            .encryptedPwd(password)
            .build();
  }
}
