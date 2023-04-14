package ideaboard.ideahub.service;

import ideaboard.ideahub.domain.IdeaBoard;
import ideaboard.ideahub.repository.IdeaBoardRepository;
import ideaboard.ideahub.repository.IdeaBoardSearch;
import ideaboard.ideahub.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 1. 리스트, 보기, 쓰기, 수정, 삭제
 * */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IdeaBoardService {

//    public final MemberRepository memberRepository;
    public final IdeaBoardRepository ideaBoardRepository;

//    public List<IdeaBoard> findIdeaBoard(IdeaBoardSearch ideaBoardSearch){
//        return ;
//    }

    //게시판 등록
    @Transactional
    public Long createBoard(IdeaBoard ideaBoard){
        if(ideaBoard.getId() == null){
            ideaBoardRepository.save(ideaBoard);
        }else{
            updateIdeaBoard(ideaBoard.getId(), ideaBoard.getTitle(), ideaBoard.getContent());
        }

        return ideaBoard.getId();
    }

    //리스트
    public List<IdeaBoard> findIdeaBoards(){
        List<IdeaBoard> all = ideaBoardRepository.findAll();
        return all;

    }

    //보기 -
    public IdeaBoard findOne(Long ideaBoardId){
        return ideaBoardRepository.findOne(ideaBoardId);
    }

    //업데이트
    @Transactional
    public void updateIdeaBoard(Long ideaBoardId, String title, String content){
        IdeaBoard ideaBoard = ideaBoardRepository.findOne(ideaBoardId);

        ideaBoard.update(title,content);

    }

}
