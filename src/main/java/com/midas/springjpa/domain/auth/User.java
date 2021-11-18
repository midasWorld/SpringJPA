package com.midas.springjpa.domain.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter @Setter
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
    private String name;

    private String password;
}