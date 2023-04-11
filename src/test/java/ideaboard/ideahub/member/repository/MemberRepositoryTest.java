package ideaboard.ideahub.member.repository;

import ideaboard.ideahub.member.domain.Member;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member("loginId", "이름", "password1!");


        //when
        memberRepository.save(member);


        Member member1 = memberRepository.findOne(member.getId());
        em.flush();
        //then
        assertEquals("두객체가 다르다",member,member1);
        assertEquals("두객체가 다르다2","2",member1);

    }

}