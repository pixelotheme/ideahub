package ideaboard.ideahub.ideaboard.domain;

import ideaboard.ideahub.member.domain.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 필드 : idea_board_id, title, content, write_date, update_date,
 * */

@Entity
@Getter
public class IdeaBoard {

    @Id @GeneratedValue
    @Column(name = "idea_board_id")
    private Long id;

    private String title;
    private String content;
    private LocalDateTime writeDate;
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    //==연관관계 편의 메소드
    private IdeaBoard(Member member){
        this.member = member;
        member.getIdeaBoards().add(this); // 현재클래스 객체를 넣어준다
    }
}
