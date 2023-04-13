package ideaboard.ideahub.domain;

import ideaboard.ideahub.domain.Member;
import lombok.Builder;
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

    @Builder
    public IdeaBoard(String title, String content, LocalDateTime writeDate, LocalDateTime updateDate, Member member) {
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
        this.updateDate = updateDate;
        this.member = member;
    }
    //메소드를 통한 업데이트
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //==연관관계 편의 메소드
    private void setMember(Member member){
        this.member = member;
        member.getIdeaBoards().add(this); // 현재클래스 객체를 넣어준다
    }


}
