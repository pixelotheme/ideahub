package ideaboard.ideahub.repository;

import ideaboard.ideahub.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

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

        Member member = Member.builder()
                .loginId("hoan0418")
                .name("이름")
                .password("tmdghks1!")
                .signDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        //when
        memberRepository.save(member);


        Member member1 = memberRepository.findOne(member.getId());
        em.flush();
        //then
        assertEquals("두객체가 다르다",member,member1);

    }

}