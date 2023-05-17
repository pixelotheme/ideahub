package ideaboard.ideahub.web;

import ideaboard.ideahub.config.auth.LoginUser;
import ideaboard.ideahub.config.auth.dto.SessionUser;
import ideaboard.ideahub.service.IdeaBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final IdeaBoardService ideaBoardService;
    //@LoginUser 어노테이션 설정으로 인한 session 정보 간소화
//    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        //세션정보를 가져오는 어노테이션을 만들어 중복 코드 최소화
        //CustomOAuth2UserService 에서 로그인 성공시 세션에 SessionUser를 저장했다
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

}
