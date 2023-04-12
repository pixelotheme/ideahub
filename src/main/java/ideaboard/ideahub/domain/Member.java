package ideaboard.ideahub.domain;



import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 필드 : member_id, loginId, name, password, sign_date, update_date
 * */

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;


    private String loginId;

    private String name;

    private String password;

    private LocalDateTime signDate;
    private LocalDateTime updateDate;

    // IdeaBoard 클래스의 member 필드에 매핑 한다
    @OneToMany(mappedBy = "member")
    private List<IdeaBoard> ideaBoards = new ArrayList<IdeaBoard>();

    @Builder
    public Member( String loginId, String name, String password, LocalDateTime signDate, LocalDateTime updateDate) {

        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.signDate = signDate;
        this.updateDate = updateDate;
    }
}
