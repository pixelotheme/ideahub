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
@Transactional
public class IdeaBoardService {

    public final MemberRepository memberRepository;
    public final IdeaBoardRepository ideaBoardRepository;

//    public List<IdeaBoard> findIdeaBoard(IdeaBoardSearch ideaBoardSearch){
//        return ;
//    }

}
