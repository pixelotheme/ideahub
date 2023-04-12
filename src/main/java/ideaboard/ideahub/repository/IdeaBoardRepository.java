package ideaboard.ideahub.repository;

import ideaboard.ideahub.domain.IdeaBoard;
import ideaboard.ideahub.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

//순수하게 엔티티를 조회, 저장 하는곳이다
@Repository
@RequiredArgsConstructor
public class IdeaBoardRepository {

    private final EntityManager em;


    //게시판 찾기
    public IdeaBoard findOne(Long id){
        return em.find(IdeaBoard.class, id);
    }

    public List<IdeaBoard> findAll(){
        return em.createQuery("select ib from IdeaBoard ib", IdeaBoard.class)
                .getResultList();
    }

    //검색을 위해서 JPQL이 필요하다
    public List<IdeaBoard> findBytitle(String title){
//        return em.find(Member.class, member.getName());
        return em.createQuery("select ib from IdeaBoard ib " +
                        " where ib.title = :title", IdeaBoard.class)
                .setParameter("title",title)
                .getResultList();

    }

    //저장 이자 수정의 역할또한 갖는다
    public void save(IdeaBoard ideaBoard){

        em.persist(ideaBoard);
    }

}
