package ideaboard.ideahub.repository;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
public class IdeaBoardRepositoryTest {

//    @Autowired
//    private IdeaBoardRepository ideaBoardRepository;
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private EntityManager em;
//
//    @Test
//    public void 아이디어게시판등록() throws Exception {
//        //given
//
//
//        IdeaBoard ideaBoard = IdeaBoard.builder()
//                .title("아이디어제목")
//                .content("내용테스트")
//                .writeDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
//                .build();
//        //when
//        System.out.println("ideaBoard.getId() = " + ideaBoard.getId());
//
//        ideaBoardRepository.save(ideaBoard);
//        em.flush();
//
//        System.out.println("ideaBoard.getId() 1111= " + ideaBoard.getId());
//
//        //then
//        IdeaBoard board = ideaBoardRepository.findOne(ideaBoard.getId());
//        List<IdeaBoard> bytitle = ideaBoardRepository.findBytitle(ideaBoard.getTitle());
//
//        Assert.assertEquals("다르다", ideaBoard, board);
//        Assert.assertEquals("다르다", board.getTitle(), bytitle.get(0).getTitle());
//    }

}