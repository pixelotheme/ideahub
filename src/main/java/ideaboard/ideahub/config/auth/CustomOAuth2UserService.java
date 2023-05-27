package ideaboard.ideahub.config.auth;

import ideaboard.ideahub.config.auth.dto.OAuthAttributes;
import ideaboard.ideahub.config.auth.dto.SessionUser;
import ideaboard.ideahub.domain.User;
import ideaboard.ideahub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * 구글 로그인 이후 가져온 사용자의 정보 들을 기반으로
 * 가입 및 정보수정, 세션 저장 등의 기능을 지원
 */

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest); // 로그인 된 user 정보 받은 값

        //현재 로그인 진행 중인 서비스를 구분하는 코드
        //네이버인지 구글인지 구분하기 위함 - 같이 지원할때 문제가 된다 - Naver, Google 로 반환
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //OAuth2 로그인 진행 시 키가 되는 필드값 지정 = PK
        //구글의 경우 기본적으로 코드를 지원 - "sub" , 네이버 카카오 기본지원 x - "id"라고 직접 넣어준다
        //네이버, 구글 동시 지원시 사용
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
        //이후 네이버 등 다른 소셜 로그인도 해당 클래스 사용 - oAuth2User.getAttributes() 에 name, email...정보가 있다
        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());


        User user = saveOrUpdate(attributes);
        //세션에 사용자 정보를 저장하기 위한 Dto 클래스
        //만약 user 클래스를 직접 사용한다면 직렬화 기능을 구현하지 않았다는 에러가 나온다
        httpSession.setAttribute("user", new SessionUser(user));

       return new DefaultOAuth2User(Collections
               .singleton(
                       new SimpleGrantedAuthority(user.getRoleKey())
                        ),
               attributes.getAttributes(),
               attributes.getNameAttributeKey()
               );
    }
    //로그인한 유저 db에 반영 - email 이 있다면 update
    private User saveOrUpdate(OAuthAttributes attributes) {

        User user = userRepository
                .findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
