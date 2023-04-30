package ideaboard.ideahub.web.dto;

import ideaboard.ideahub.domain.IdeaBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//생성을 위한 dto
@Getter
@NoArgsConstructor
public class IdeaBoardCreateDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public IdeaBoardCreateDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public IdeaBoard toEntity(){
        return IdeaBoard.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
