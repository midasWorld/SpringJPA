package com.midas.springjpa.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Getter @Setter
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "사용자 이메일을 입력해 주세요.")
    private String email;

    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")
    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
    private String name;

    @ApiModelProperty(notes = "사용자 비밀번호를 입력해 주세요.")
    private String password;
    @ApiModelProperty(notes = "사용자 주민번호를 입력해 주세요.")
    private String ssn;
}
