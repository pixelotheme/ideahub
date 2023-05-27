package ideaboard.ideahub.web.api;

import ideaboard.ideahub.config.auth.dto.SessionUser;
import ideaboard.ideahub.domain.User;
import ideaboard.ideahub.repository.UserRepository;
import ideaboard.ideahub.service.IdeaBoardService;
import ideaboard.ideahub.web.dto.IdeaBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class IdeaBoardApiController {

    private final IdeaBoardService ideaBoardService;
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    //글 등록 - TODO - ReponseEntity 리턴타입으로 optional null 처리 해줘야 한다
    @PostMapping("/web/api/ideaboard/post")
    public Long createBoard(@RequestBody IdeaBoardDto ideaBoardDto){

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        Optional<User> optionalUser = userRepository.findByEmail(sessionUser.getEmail());

        User user = optionalUser.get();

        IdeaBoardDto dto = IdeaBoardDto.builder()
                .title(ideaBoardDto.getTitle())
                .content(ideaBoardDto.getContent())
                .user(user)
                .writeDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        return ideaBoardService.createBoard(dto.toEntity());
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
