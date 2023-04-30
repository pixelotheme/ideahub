package ideaboard.ideahub.web.api;

import ideaboard.ideahub.service.IdeaBoardService;
import ideaboard.ideahub.web.dto.IdeaBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class IdeaBoardApiController {

    private final IdeaBoardService ideaBoardService;

    //글 등록
    @PostMapping("/web/api/ideaboard/post")
    public Long createBoard(@RequestBody IdeaBoardDto ideaBoardDto){
        return ideaBoardService.createBoard(ideaBoardDto.toEntity());
    }

    @PutMapping("/web/api/ideaboard/update")
    public Long updateBoard(@RequestBody IdeaBoardDto ideaBoardDto){


        return ideaBoardService.updateIdeaBoard(ideaBoardDto.getId(),ideaBoardDto.getTitle(), ideaBoardDto.getContent());
    }

    @DeleteMapping("/web/api/ideaboard/delete/{id}")
    public Long deleteBoard(@PathVariable("id") Long id){
        return ideaBoardService.deleteIdeaBoard(id);
    }
}
