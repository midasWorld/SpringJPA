package com.midas.springjpa.domain.auth;

import com.midas.springjpa.domain.departments.Department;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.midas.springjpa.domain.auth.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional @Commit
@ActiveProfiles("test")
@SpringBootTest
class MemberTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    void testEntity() {
        queryFactory = new JPAQueryFactory(em);

        Department department1 = new Department("인사팀");
        Department department2 = new Department("재무팀");

        em.persist(department1);
        em.persist(department2);

        Member a = Member.builder().email("A@gmail.com").name("A").encryptedPwd("1234").build();
        Member b = Member.builder().email("B@gmail.com").name("B").encryptedPwd("1234").build();
        Member c = Member.builder().email("C@gmail.com").name("C").encryptedPwd("1234").build();

        a.changeDepartment(department1);
        b.changeDepartment(department2);
        c.changeDepartment(department1);

        em.persist(a);
        em.persist(b);
        em.persist(c);

        em.flush();
    }

    @Test
    public void startQueryDsl() {
        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.name.eq("A"))
                .fetchOne();
        assertThat(findMember.getName()).isEqualTo("A");
    }

    @Test
    public void search() {
        Member findMember = queryFactory
                .selectFrom(QMember.member)
                .where(
                        member.name.eq("A"),
                        member.encryptedPwd.eq("1234")
                )
                .fetchOne();

        assertThat(findMember.getName()).isEqualTo("A");
    }
}