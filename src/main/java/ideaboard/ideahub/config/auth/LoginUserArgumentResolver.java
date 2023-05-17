package ideaboard.ideahub.config.auth;

import ideaboard.ideahub.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

/**
 * HandlerMethodArgumentResolver 인터페이스를 구현한다
 * 조건에 맞는 경우 메소드가 있다면 HandlerMethodArgumentResolver의 구현체가 지정한 값으로
 * 해당 메소드의 파라미터로 넘길수 있다
 *
 *
 * */

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    /**
     * 구현 - 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단
     *
     * */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        // 파라미터에 @LoginUser 어노테이션이 있는지
        boolean isLoginUserAnnotaion = parameter.getParameterAnnotation(LoginUser.class) != null;
        //파라미터 클래스 타입이 SessionUser.class 타입인지
        // CustomOAuth2UserService에서 session에 등록해준다
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotaion && isUserClass;
    }

    //파라미터에 전달할 객체 생성
    //세션 객체를 가져온다
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        return httpSession.getAttribute("user");
    }
}
