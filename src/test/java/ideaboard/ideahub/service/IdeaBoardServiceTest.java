package ideaboard.ideahub.service;

import ideaboard.ideahub.domain.IdeaBoard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class IdeaBoardServiceTest {

    @Autowired
    private IdeaBoardService ideaBoardService;
    @Autowired
    private EntityManager em;
    
    @Test
    public void 게시판등록() throws Exception {
        //given
        IdeaBoard ideaBoard = getIdeaBoard1();
        IdeaBoard ideaBoard2 = getIdeaBoard2();
        //when
        System.out.println("ideaBoard.getId() = " + ideaBoard.getId());
        Long ideaBoardId = ideaBoardService.createBoard(ideaBoard);
        Long ideaBoardId2 = ideaBoardService.createBoard(ideaBoard2);

        IdeaBoard findIdeaBoard = ideaBoardService.findOne(ideaBoardId);
        em.flush();
        //then
        
        Assert.assertEquals("게시판이 다르다",findIdeaBoard.getId(),ideaBoardId);
        Assert.assertEquals("게시판이 다르다",ideaBoard.getId(),ideaBoardId);
        Assert.assertEquals("게시판이 다르다",ideaBoard.getId(),ideaBoardId2);
    }

    @Test
    public void 수정() throws Exception {
        //given
        IdeaBoard ideaBoard = getIdeaBoard1();

        //when
        Long boardId = ideaBoardService.createBoard(ideaBoard);


        ideaBoardService.updateIdeaBoard(boardId, "제목수정", "내용수정");
        //then
        Assert.assertEquals("수정 제목이 다르다", "제목수정", ideaBoard.getTitle());
        Assert.assertEquals("수정 내용이 다르다", "내용수정", ideaBoard.getContent());
    }


    private IdeaBoard getIdeaBoard1() {
        IdeaBoard ideaBoard = IdeaBoard
                .builder()
                .title("게시판제목")
                .content("게시판내용")
                .writeDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        return ideaBoard;
    }

    private IdeaBoard getIdeaBoard2() {
        IdeaBoard ideaBoard2 = IdeaBoard
                .builder()
                .title("게시판제목2")
                .content("게시판내용2")
                .writeDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        return ideaBoard2;
    }
}