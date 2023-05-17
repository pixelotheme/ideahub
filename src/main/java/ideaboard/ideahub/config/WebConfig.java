package ideaboard.ideahub.config;

import ideaboard.ideahub.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 *  LoginUserArgumentResolver가 스프링에서 인식될 수 있도록 WebMvcConfigurer 에 추가
 * */

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    /**
     * HandlerMethodArgumentResolver는
     * 항상 WebMvcConfigurer의 addArgumentResolvers() 메서드를 통해 추가해야한다
     *
     * 추가적으로 HandlerMethodArgumentResolver가 필요하다면 아래와 같이 추가해 준다
     * */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
