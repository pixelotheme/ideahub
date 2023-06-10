package ideaboard.ideahub.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

//단순 Enviroment profile 컨트롤러를 테스트할때는 스프링 환경이 필요없다
 // SpringSecurityConfig 설정을 불러와야해 추가
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProfileControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("profile 인증 없이 호출 테스트")
    public void profileNoAuthTest(){
        String expected = "default";

        ResponseEntity<String> response = restTemplate.getForEntity("/profile", String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(expected);

    }

    @Test
    @DisplayName("real1 profile 조회")
    public void real1Profile(){
        //given
        String expectedProfile = "real1";
        //Environment - 는 인터페이스라 Mock으로 가짜 구현체인을 사용
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");
        env.addActiveProfile("real-oauth");

        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        Assertions.assertThat(profile).isEqualTo(expectedProfile);
    }
    @Test
    @DisplayName("real1 profile이 없으면 첫 번째가 조회된다")
    public void real1ProfileNotExist(){
        //given
        String expectedProfile = "real-oauth";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        Assertions.assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    @DisplayName("active된 profile이 없으면 default가 조회된다")
    public void activeProfileNotExist(){
        //given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();

        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        Assertions.assertThat(profile).isEqualTo(expectedProfile);
    }

}