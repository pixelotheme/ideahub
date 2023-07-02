package ideaboard.ideahub.config.auth;

import ideaboard.ideahub.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity // spring Security 설정들을 활정화 시킨다
//WebSecurityConfigurerAdapter 사용 불가로 인한 추가
@Configuration(proxyBeanMethods = false)
//@ConditionalOnDefaultWebSecurity // 스프링 빈으로 자동 등록된 객체와 중복되어 선택필요
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()// h2-console 화면 사용위해 해당 옵션 disable
                .and()
                    .authorizeHttpRequests()//url별 권한 관리를 설정하는 옵션의 시작점 - authorize가 선언되어야만 antMachers 옵션 사용 가능
                // antMatchers - 권한 관리 대상을 지정하는 옵션 - url, http 메소드별로 관리가능
                // "/"등 지정된 URL들은 permitAll()옵션을 통해 전체 열람권한을 주었다
                //  /profile 제외 무중단 배포로인한 추가
                    .antMatchers("/","/web/ideaboard/**", "/css/**","/images/**",
                            "/js/**", "/h2-console/**","/profile").permitAll()
                    .antMatchers("/web/api/**").hasRole(Role.USER.name())///web 주소를 가진 api는 user 권한을 가진 사람만 가능하도록 설정
                    .anyRequest().authenticated() // 설정된 값들 이외 나머지 URL들을 나타낸다, authenticated - 나머지 URL들은 모두 인증된 사용자에게만 허용(= 로그인한 사용자)
                .and()
                    .logout()
                    .logoutSuccessUrl("/")//로그아웃 기능에 대한 여러 설정의 진입점 - 로그아웃 성공시 / 주소로 이동
                .and()
                    .oauth2Login()//로그인 기능에 대한 설정의 진입점
                    .userInfoEndpoint()// 로그인 성공 이후 사용자 정보를 가져올 때의 설정들 담당
                //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                //리 소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는기능을 명시할 수 있다
                    .userService(customOAuth2UserService);
        //5.7 부터 WebSecurityConfigurerAdapter 상속을 못쓰고 return 타입을 넣어줘야 한다
        return http.build();
    }
}
