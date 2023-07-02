package ideaboard.ideahub.web.api;

import ideaboard.ideahub.domain.user.Role;
import ideaboard.ideahub.domain.user.User;
import ideaboard.ideahub.repository.IdeaBoardRepository;
import ideaboard.ideahub.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager em;

    //스프링 시큐리티 테스트 하기위한 MockMVC 사용
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;


    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
//        ideaBoardRepository.delete();
    }

    // - TODO 회원 까지 테스트하는 검증이 필요함 - 클라이언트 단에서 세션 이메일 꺼내는 방법으로?
//    @Test
//    @DisplayName("아이디어 보드 등록")
//    @WithMockUser(roles = "USER") //MockMVC 에서만 작동한다
//    public void ideaBoardCreateTest() throws Exception {
//        //given
//
//        String title = "제목";
//        String content = "내용테스트";
//        IdeaBoardCreateDto createDto = IdeaBoardCreateDto
//                .builder()
//                .title(title)
//                .content(content)
//                .author("author")
//                .build();
//        String url = "http://localhost:"+port+"/web/api/ideaboard/post";
//
//
//        //when
//        //생성된 MockMvc를 통해 api 테스트
//        //content 영역은 문자열로 표현하기 위해 ObjectMapper를 통해 문자열JSON으로 변환
//        //{"title":"제목","content":"내용테스트","author":"author"}
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(createDto)))
//                .andExpect(status().isOk());
//
//        //then
//
//        List<IdeaBoard> all = ideaBoardRepository.findAll();
//        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(title);
//        Assertions.assertThat(all.get(0).getContent()).isEqualTo(content);
//
//    }

    @Test
    public void UserTest() throws Exception{
        User user = User.builder()
                .email("hoho@AA")
                .name("테스트")
                .role(Role.USER)
                .build();
        em.persist(user);
//        userRepository.save(user);
        em.flush();
        em.clear();

        System.out.println(em.find(User.class,user.getId()));
    }

}