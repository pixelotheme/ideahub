package ideaboard.ideahub.service;

import ideaboard.ideahub.domain.Member;
import ideaboard.ideahub.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *  - Repository 에서 만들어진 것을 조합해 사용한다
 * 1. 회원등록, 수정, 삭제, 리스트
 * 2. 중복 체크
 * */

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(Member member){
        DuplicateCheck(member);
        memberRepository.save(member); // 저장시 id 가 생긴다 - 영속석 컨텍스트 의 관리 대상이됨
        //쿠키나 세션에 id 를 저장해 두었다 사용할수 있을듯
        return member.getId();
    }

    public void DuplicateCheck(Member member){
        List<Member> byLoginId = memberRepository.findByLoginId(member.getLoginId());
        if(!byLoginId.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public Member login(String loginId, String password){
        //1개가 안나오면 에러가 난다고 한다 확인해봐야 할듯
        try {
            Member member = memberRepository.login(loginId, password);
            return member;
        }catch (NoResultException  e){
            throw new NoResultException("아이디가 없습니다");
        }catch (NonUniqueResultException n){
            throw new NonUniqueResultException("해당하는 아이디가 2개 이상");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //로그인 후 수정 확인 해봐야함
    public void update(String loginId, String password , String name){
        Member member = login(loginId, password);

        member = Member.builder().name(name).updateDate(LocalDateTime.now()).build();
    }

}
