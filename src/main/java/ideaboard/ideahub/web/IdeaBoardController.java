package ideaboard.ideahub.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IdeaBoardController {

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/ideaboard/list")
    public String list(){
        return "/ideaboard/list";
    }

    @GetMapping("/ideaboard/createForm")
    public String createForm(){
        return "/ideaboard/createForm";
    }

}
