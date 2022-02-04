package com.midas.springjpa.domain.users;

import com.midas.springjpa.domain.BaseTimeEntity;
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
public class UserEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String password;

    @Builder
    public UserEntity(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    //== 비지니스 로직 ==//
    public void update(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
