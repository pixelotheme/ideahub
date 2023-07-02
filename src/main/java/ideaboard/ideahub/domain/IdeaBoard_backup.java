package ideaboard.ideahub.domain;

import ideaboard.ideahub.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 필드 : idea_board_id, title, content, write_date, update_date,
 * */

//@Entity
//@Getter
//@NoArgsConstructor
public class IdeaBoard_backup {

    //IDENTITY로 지정해 줘야 mariadb auto_increament가 된다
    // 엔티티가 영속상태가 되려면 식별자가 필요한데 IDENTITY 전략인 insert 이 식별자를 구할수 있다.
    //그래서 persist 호출 즉시 쿼리가 들어가고 트랜잭션 쓰기 지연이 작동하지 않는다.
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "idea_board_id")
//    private Long id;
//
//    @Column(length = 500, nullable = false)
//    private String title;
//    @Column(columnDefinition = "TEXT", nullable = false)
//    private String content;
//
//    private String author;
//
//    private LocalDateTime writeDate;
//
//    private LocalDateTime updateDate;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//
//    @Builder
//    public IdeaBoard_backup(String title, String content, LocalDateTime writeDate, LocalDateTime updateDate, String author) {
//        this.title = title;
//        this.content = content;
//        this.writeDate = writeDate;
//        this.updateDate = updateDate;
//        this.author = author;
////        this.user = user; user 오픈시 배포 ㅇ안됨
//    }
//    //메소드를 통한 업데이트
//    public void update(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

    //==연관관계 편의 메소드
//    private void setMember(Member member){
//        this.member = member;
//        member.getIdeaBoards().add(this); // 현재클래스 객체를 넣어준다
//    }


}
