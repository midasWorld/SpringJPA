package com.midas.springjpa.web.dto.member;

import com.midas.springjpa.domain.auth.Member;
import com.midas.springjpa.web.valid.NoEmoji;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

  @Email(message = "이메일 형식이 올바르지 않습니다.")
  @NotNull
  private String email;

  @NoEmoji
  @NotNull @NotEmpty
  private String name;

  @NotNull @NotEmpty
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
