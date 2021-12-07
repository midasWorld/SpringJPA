package com.midas.springjpa.domain.auth;

import com.midas.springjpa.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;

    private String encryptedPwd;

    @Builder
    public User(String email, String name, String encryptedPwd) {
        this.email = email;
        this.name = name;
        this.encryptedPwd = encryptedPwd;
    }

    //== 비지니스 로직 ==//
    public void update(String name, String password) {
        this.name = name;
        this.encryptedPwd = password;
    }
}
