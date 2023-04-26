package ideaboard.ideahub.api;

import ideaboard.ideahub.domain.IdeaBoard;
import ideaboard.ideahub.service.IdeaBoardService;
import ideaboard.ideahub.web.dto.IdeaBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class IdeaBoardApiController {

    private final IdeaBoardService ideaBoardService;


    @PostMapping("/api/ideaboard/post")
    public Long createBoard(@RequestBody IdeaBoardDto ideaBoardDto){
        return ideaBoardService.createBoard(ideaBoardDto.toEntity());
    }

    @PutMapping("/api/ideaboard/update")
    public Long updateBoard(@RequestBody IdeaBoardDto ideaBoardDto){


        return ideaBoardService.updateIdeaBoard(ideaBoardDto.getId(),ideaBoardDto.getTitle(), ideaBoardDto.getContent());
    }

    @DeleteMapping("/api/ideaboard/delete/{id}")
    public Long deleteBoard(@PathVariable("id") Long id){
        return ideaBoardService.deleteIdeaBoard(id);
    }
}
