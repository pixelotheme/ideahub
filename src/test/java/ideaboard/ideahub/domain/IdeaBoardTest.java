package ideaboard.ideahub.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class) //테스트시 junit에 내장된 실행자 외에 다른 실행자 실행 = SpringRunner -> junit과 springboot 를 연결해주는 어노테이션
@SpringBootTest
@Transactional
class IdeaBoardTest {


    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    public void test() {
        User user1 = User.builder()
                .name("이름1")
                .email("test1@gamil")
                .role(Role.USER)
                .build();
        User user2 = User.builder()
                .name("이름2")
                .email("test2@gamil")
                .role(Role.USER)
                .build();

        IdeaBoard board1 = IdeaBoard
                .builder()
                .title("제목1")
                .content("내용1")
//                .user(user1)
                .build();
        IdeaBoard board2 = IdeaBoard
                .builder()
                .title("제목2")
                .content("내용2")
//                .user(user2)
                .build();


        em.persist(user1);
        em.persist(user2);
        em.persist(board1);
        em.persist(board2);

        em.flush();
        em.clear();
    }
//
//    @Test
//    @DisplayName(value = "게시판 user id 등록")
//    public void saveBoard() throws Exception{
//        //given
//        //when
//
//        List<IdeaBoard> resultList = em.createQuery("select ib from IdeaBoard ib", IdeaBoard.class)
//                .getResultList();
//
//        //then
//        resultList.stream().forEach(r -> assertThat(r.getUser().getId())
//                        .isEqualTo(em.find(User.class, r.getUser().getId()).getId())
//                                    );
//
//    }
//
//    @Test
//    @DisplayName(value = "게시판 삭제")
//    public void deleteBoard() throws Exception{
//        //given
//        List<User> findUser = em.createQuery("select u from User u ", User.class).getResultList();
//
//        //when
//        findUser.stream().forEach(
//                u -> assertThat(countUserIdeaBoard(u.getId())
//                ).isEqualTo(em.createQuery("delete from IdeaBoard ib where ib.user.id = :user_id ")
//                        .setParameter("user_id", u.getId())
//                        .executeUpdate())
//        );
//    }
//
//    public Long countUserIdeaBoard(Long userId){
//        return em.createQuery("select count(ib) from IdeaBoard ib where ib.user.id = :user_id", Long.class)
//                .setParameter("user_id", userId)
//                .getSingleResult();
//    }
}