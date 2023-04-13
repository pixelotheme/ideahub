package ideaboard.ideahub.api;

import ideaboard.ideahub.domain.IdeaBoard;
import ideaboard.ideahub.service.IdeaBoardService;
import ideaboard.ideahub.web.dto.IdeaBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class IdeaBoardApiController {

    private final IdeaBoardService ideaBoardService;

    @GetMapping("/api/ideaboard/getlist")
    public List<IdeaBoardDto> getList(){
        List<IdeaBoard> ideaBoards = ideaBoardService.findIdeaBoards();

        List<IdeaBoardDto> collect = ideaBoards.stream().map(o -> IdeaBoardDto
                        .builder()
                        .title(o.getTitle())
                        .content(o.getContent())
                        .writeDate(o.getWriteDate())
                        .updateDate(o.getUpdateDate())
                        .build())
                .collect(Collectors.toList());

        return collect;
    }

    @GetMapping("/api/ideaboard/get/{id}")
    public IdeaBoardDto get(@PathVariable("id") Long id){
        IdeaBoard ideaBoard = ideaBoardService.findOne(id);
        IdeaBoardDto ideaBoardDto = IdeaBoardDto
                .builder()
                .id(id)
                .title(ideaBoard.getTitle())
                .content(ideaBoard.getContent())
                .writeDate(ideaBoard.getWriteDate())
                .updateDate(ideaBoard.getUpdateDate())
                .build();
        return ideaBoardDto;
    }
    @PostMapping("/api/ideaboard/post")
    public Long createBoard(@RequestBody IdeaBoardDto ideaBoardDto){
        return ideaBoardService.createBoard(ideaBoardDto.toEntity());
    }
}
