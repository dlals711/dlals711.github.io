package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // junit실행할때 spring과 엮어서 실행
@SpringBootTest
@Transactional // 롤백하려고
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception{
        //given 이런게 주어졌을때
        Member member = new Member();
        member.setName("kim");

        //when 실행했을때
        Long saveId = memberService.join(member);

        //then 이렇게 된다
        assertEquals(member, memberRepository.findOne(saveId));

    }



    @Test(expected = IllegalStateException.class) //
    public void 중복_회원_예외() throws Exception {
        //given 이런게 주어졌을때
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when 실행했을때
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생해야 한다.

        //then 이렇게 된다
        fail("예외가 발생해야 한다.");

    }
}