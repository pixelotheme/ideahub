package ideaboard.ideahub.config.auth.dto;

import ideaboard.ideahub.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * 인증된 사용자 정보만 필요
 * 만약 user 클래스를 직접 사용한다면 직렬화 기능을 구현하지 않았다는 에러가 나온다
 * 엔티티는 연관관계를 통한 자식 엔티티를 갖고 있어
 * 모든 관계가 직렬화 대상이되어 성능이슈, side effect 발생 가능성 높다
 * */

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
