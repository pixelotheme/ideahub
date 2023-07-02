package ideaboard.ideahub.config.auth.dto;

import ideaboard.ideahub.domain.user.Role;
import ideaboard.ideahub.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
    //OAuth2User 에서 반환하는 사용자 정보는 Map이기 때문에
    //값 하나하나를 변환해야 한다
    public static OAuthAttributes of(String registrationId,
                                           String userNameAttributeName,
                                           Map<String, Object> attributes){
        if("naver".equals(registrationId)){
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);

    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String,Object> attributes){
        //builder로 값을 반환 받아 생성한다
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .picture((String)attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    //네이버 생성자
    private static OAuthAttributes ofNaver(String userNameAttributeName,
                                          Map<String,Object> attributes){
        //상위 필드만 지정 가능해서 response로 받아 왔다
        Map<String, Object> response = (Map<String, Object>)attributes.get("response");

        return OAuthAttributes.builder()
                .name((String)response.get("name"))
                .email((String)response.get("email"))
                .picture((String)response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    //User 엔티티 생성
    //OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때
    //가입할 때의 기본 권한을 GUEST로 주기 위해 role 빌더값에 GUEST
    //OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 SessionUser 클래스를 생성
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }

}
