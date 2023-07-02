package ideaboard.ideahub.domain;

import ideaboard.ideahub.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


//@Getter
//@NoArgsConstructor
//@Entity
public class User_bak {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//    @Column(nullable = false)
//    private String email;
//
//    @Column
//    private String picture;
//
//    private LocalDateTime createDate;
//
//    private LocalDateTime updateDate;
//
//    //enum 타입 선언
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;
//
//    @Builder
//    public User_bak(Long id, String name, String email, String picture, Role role) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.picture = picture;
//        this.role = role;
//    }
//
//    public User_bak update(String name, String picture){
//        this.name = name;
//        this.picture = picture;
//        //현재 user 객체가 반환된다
//        return this;
//    }
//
//    public String getRoleKey(){
//        return this.role.getKey();
//    }
}
