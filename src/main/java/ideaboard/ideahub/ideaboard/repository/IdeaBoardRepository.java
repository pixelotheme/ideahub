package ideaboard.ideahub.ideaboard.repository;

import ideaboard.ideahub.ideaboard.domain.IdeaBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class IdeaBoardRepository {

    private final EntityManager em;

    //게시판 찾기
    public IdeaBoard findOne(Long id){
        return em.find(IdeaBoard.class, id);
    }

    public List<IdeaBoard> findAll(){
        return em.createQuery("select ib from ideaboard ib", IdeaBoard.class)
                .getResultList();
    }

    public void save(IdeaBoard ideaBoard){
        em.persist(ideaBoard);
    }
}
