package com.midas.springjpa.domain.departments;

import com.midas.springjpa.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString(of = {"id", "name"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "departments")
@Entity
public class Department extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    private String name;

    @Builder
    public Department(String name) {
        this.name = name;
    }
}
