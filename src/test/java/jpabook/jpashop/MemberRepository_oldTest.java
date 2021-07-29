package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import jpabook.jpashop.domain.Member;

@SpringBootTest
class MemberRepository_oldTest {

    @Autowired MemberRepository_old memberRepository_old;


    @Test
    @Transactional // 테스트가 종료되고 자동으로 롤백 처리됨
    @Rollback(value = false) //테스트 종료되어도 롤백처리를 안함.
    public void testMember(){
        Member member = new Member();
        member.setUsername("memberA");
        Long savedId = memberRepository_old.save(member);
        Member findMember = memberRepository_old.find(savedId);
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성 보장

    }
    //commit test

}