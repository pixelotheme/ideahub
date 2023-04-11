package ideaboard.ideahub.member.repository;

import ideaboard.ideahub.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from member m", Member.class)
                .getResultList();
    }

    public void save(Member member){
        em.persist(member);
    }
}
