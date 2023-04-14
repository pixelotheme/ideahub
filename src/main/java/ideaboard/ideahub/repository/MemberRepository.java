package ideaboard.ideahub.repository;

import ideaboard.ideahub.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    private final EntityManager em;
//
//    public Member findOne(Long id){
//        return em.find(Member.class, id);
//    }
//
//    public List<Member> findAll(){
//        return em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//    }
//
//    //검색을 위해서 JPQL이 필요하다
//    public List<Member> findByLoginId(String loginId){
////        return em.find(Member.class, member.getName());
//        return em.createQuery("select m from Member m " +
//                " where m.loginId = :loginId", Member.class)
//                .setParameter("loginId",loginId)
//                .getResultList();
//
//    }
//
//    //로그인
//    public Member login(String loginId, String password) throws Exception{
//        return em.createQuery("select m from Member m " +
//                " where m.loginId = :loginId " +
//                " and m.password = :password", Member.class)
//                .setParameter("loginId", loginId)
//                .setParameter("password", password)
//                .getSingleResult();
//    }
//
//    public void save(Member member){
//        em.persist(member);
//    }



}
