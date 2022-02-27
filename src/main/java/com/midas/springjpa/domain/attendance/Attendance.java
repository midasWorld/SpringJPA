package com.midas.springjpa.domain.attendance;

import com.midas.springjpa.domain.BaseTimeEntity;
import com.midas.springjpa.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "attendance")
@Entity
public class Attendance extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attend_id")
    private Long id;

    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int workTime;

    @Enumerated(EnumType.STRING)
    private AttendanceState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
