package ideaboard.ideahub.web;

import ideaboard.ideahub.domain.IdeaBoard;
import ideaboard.ideahub.service.IdeaBoardService;
import ideaboard.ideahub.web.dto.IdeaBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IdeaBoardController {

    private final IdeaBoardService ideaBoardService;


//    @GetMapping("/")
//    public String home(){
//        return "index";
//    }

    @GetMapping("/web/ideaboard/list")
    public String list(Model model)
    {
        List<IdeaBoard> ideaBoards = ideaBoardService.findIdeaBoards();

        List<IdeaBoardDto> collect = ideaBoards.stream().map(o -> IdeaBoardDto
                .builder()
                .title(o.getTitle())
                .content(o.getContent())
                .writeDate(o.getWriteDate())
                .updateDate(o.getUpdateDate())
                .id(o.getId())
                .build()
        ).collect(Collectors.toList());


        model.addAttribute("list", collect);
        return "web/ideaboard/list";


    }

    @GetMapping("/web/ideaboard/createForm")
    public String createForm(){
        return "web/ideaboard/createForm";
    }

    @GetMapping("/web/ideaboard/view")
    public String view(Long id, Model model){
        IdeaBoard ideaBoard = ideaBoardService.findOne(id);

        IdeaBoardDto ideaBoardDto = IdeaBoardDto
                .builder()
                .title(ideaBoard.getTitle())
                .content(ideaBoard.getContent())
                .writeDate(ideaBoard.getWriteDate())
                .updateDate(ideaBoard.getUpdateDate())
                .id(ideaBoard.getId())
                .build();
        model.addAttribute("ideaBoard", ideaBoardDto);
        return "web/ideaboard/view";
    }

    @GetMapping("/web/ideaboard/updateForm")
    public String updateForm(Long id, Model model){
        IdeaBoard ideaBoard = ideaBoardService.findOne(id);

        IdeaBoardDto ideaBoardDto = IdeaBoardDto
                .builder()
                .title(ideaBoard.getTitle())
                .content(ideaBoard.getContent())
                .writeDate(ideaBoard.getWriteDate())
                .updateDate(ideaBoard.getUpdateDate())
                .id(ideaBoard.getId())
                .build();
        model.addAttribute("ideaBoard", ideaBoardDto);
        return "web/ideaboard/updateForm";
    }

}
