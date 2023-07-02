package ideaboard.ideahub.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // final 필드만 자동 DI 해준다
public enum Role {
    // 스프링 시큐리티에서는 권한 코드에 항상 ROLE_ 가 앞에 있어야 한다
    //그래서 키값을 ROLE_~~~ 로 지정했다
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
