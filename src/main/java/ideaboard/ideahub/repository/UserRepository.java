package ideaboard.ideahub.repository;


import ideaboard.ideahub.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
//    private final EntityManager em;
//
//    //같은 이메일을 가진 회원이 있는지 판단하기 위한 메소드
//    public List<User> findByEmail(String email){
//        String query = "select u from User u " +
//                " where u.email = :email ";
//        List<User> users = em.createQuery(query, User.class)
//                .setParameter("email", email)
//                .getResultList();
//
//        return users;
//    }
}
