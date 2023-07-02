package ideaboard.ideahub.web.dto;

import ideaboard.ideahub.domain.ideaBoard.IdeaBoard;
import ideaboard.ideahub.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class IdeaBoardDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime writeDate;
    private LocalDateTime updateDate;
    private User user;

    @Builder
    public IdeaBoardDto(Long id,String title, String content, LocalDateTime writeDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
        this.updateDate = updateDate;
//        this.user = user;
    }

    public IdeaBoard toEntity(){
        return IdeaBoard.builder()
                .title(title)
                .content(content)
                .writeDate(writeDate)
                .updateDate(updateDate)
//                .user(user)
                .build();
    }
}
