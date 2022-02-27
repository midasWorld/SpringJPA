package com.midas.springjpa.domain.auth;

import com.midas.springjpa.domain.BaseTimeEntity;
import com.midas.springjpa.domain.departments.Department;
import com.midas.springjpa.domain.position.Position;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@ToString(of = {"id", "email", "name"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members", uniqueConstraints = @UniqueConstraint(
        name = "unq_member_email",
        columnNames = "email"
))
@Entity
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private String encryptedPwd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @Builder
    public Member(String email, String name, String encryptedPwd, Department department, Position position) {
        this.email = email;
        this.name = name;
        this.encryptedPwd = encryptedPwd;
        if (department != null) {
            changeDepartment(department);
        }
        if (position != null) {
            changePosition(position);
        }
    }

    //== 비지니스 로직 ==//
    public void changeDepartment(Department department) {
        this.department = department;
    }

    public void changePosition(Position position) {
        this.position = position;
    }
}
