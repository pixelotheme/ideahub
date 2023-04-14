package ideaboard.ideahub.repository;

import ideaboard.ideahub.domain.IdeaBoard;
import ideaboard.ideahub.domain.Member;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class IdeaBoardRepositoryTest {

    @Autowired
    private IdeaBoardRepository ideaBoardRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void 아이디어게시판등록() throws Exception {
        //given
//        Member member = Member.builder()
//                .loginId("hoan0418")
//                .name("이름")
//                .password("tmdghks1!")
//                .signDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
//                .build();
//        memberRepository.save(member);

        IdeaBoard ideaBoard = IdeaBoard.builder()
                .title("아이디어제목")
                .content("내용테스트")
                .writeDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
//                .member(member)
                .build();
        //when
        System.out.println("ideaBoard.getId() = " + ideaBoard.getId());

        ideaBoardRepository.save(ideaBoard);
        em.flush();

        System.out.println("ideaBoard.getId() 1111= " + ideaBoard.getId());

        //then
        IdeaBoard board = ideaBoardRepository.findOne(ideaBoard.getId());
        List<IdeaBoard> bytitle = ideaBoardRepository.findBytitle(ideaBoard.getTitle());

        Assert.assertEquals("다르다", ideaBoard, board);
        Assert.assertEquals("다르다", board.getTitle(), bytitle.get(0).getTitle());
    }

}