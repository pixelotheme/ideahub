package ideaboard.ideahub.service;

import ideaboard.ideahub.domain.Member;
import ideaboard.ideahub.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
public class MemberServiceTest {

//    @Autowired
//    private MemberService memberService;
//    @Autowired
//    private EntityManager em;
//
//    @Test
//    public void 회원가입() throws Exception {
//        //given
//
//        Member member = setMember();
//
//        Member member1 = setMember1();
//        //when
//        Long memberId = memberService.join(member);
//        Long memberId1 = memberService.join(member1);
//
//        em.flush();
//
//        //then
//        Assert.assertEquals("다르다", memberId, member);
//        Assert.assertEquals("다르다", memberId1, member1);
//
//    }
//    @Test
//    public void 로그인() throws Exception {
//        //given
//        Member member = setMember();
//
//        //when
//        Long join = memberService.join(member);
//        em.flush();
//        //then
////        Member login = memberService.login("hoan0418", "Tmdghks1!");
//        Member login = memberService.login("hoan0418", "Tmdghks1!");
//
//
//        Assert.assertEquals("로그인 불가", member, login);
//        fail();
//
//    }
//
//    //update 테스트 필요
//
//    private Member setMember() {
//        Member member = Member.builder()
//                .loginId("hoan0418")
//                .name("이름")
//                .password("tmdghks1!")
//                .signDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
//                .build();
//        return member;
//    }
//
//    private Member setMember1() {
//        Member member1 = Member.builder()
//                .loginId("hoan04188")
//                .name("이름1")
//                .password("tmdghks1!")
//                .signDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
//                .build();
//        return member1;
//    }


}