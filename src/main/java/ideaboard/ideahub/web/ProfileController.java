package ideaboard.ideahub.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 배포시 8001,8002 포트 선택 기준
 *
 * 해당 컨트롤러를 테스트할때는 스프링 환경이 필요없다
 * */

@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final Environment env;


    @GetMapping("/profile")
    public String profile(){
        //현재 실행중인 ActiveProfile 모두 가져온다 - real, real-db, real-oauth 활성화중이라면 3개 들어간다 - 현재 운영시 active profile 들
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        //배포에 사용될 profile들 - 이중 하나가 있다면 그 값을 반환
        //- 실제 real2,real3 만 무중단 배포에서 사용 - profile group 명
        List<String> realProfiles = Arrays.asList("real1", "real2", "real3");

        String defaultProfile = profiles.isEmpty()? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
