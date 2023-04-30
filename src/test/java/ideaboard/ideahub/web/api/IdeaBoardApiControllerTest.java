package ideaboard.ideahub.web.api;

import ideaboard.ideahub.domain.IdeaBoard;
import ideaboard.ideahub.repository.IdeaBoardRepository;
import ideaboard.ideahub.web.dto.IdeaBoardCreateDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//@WebMvcTest 의 경우 Controller와 ControllerAdvice 등
// 외부 연동과 관련된 부분만 활성화 되어 JPA 기능이 작동하지 않아
//@SpringBootTest와 TestRestTemplate 사용한다
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class IdeaBoardApiControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private IdeaBoardRepository ideaBoardRepository;

    @After
    public void tearDown() throws Exception {
//        ideaBoardRepository.delete();
    }

    @Test
    @DisplayName("아이디어 보드 등록")
    public void ideaBoardCreateTest() throws Exception {
        //given
        String title = "제목";
        String content = "내용테스트";
        IdeaBoardCreateDto createDto = IdeaBoardCreateDto
                .builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        String url = "http://localhost:"+port+"/web/api/ideaboard/post";

        //when
        //커스텀 dto를 넘겨준뒤 - Long 타입인 pk 값 을 받는다
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, createDto, Long.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<IdeaBoard> all = ideaBoardRepository.findAll();
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(content);

        //then
    }


}