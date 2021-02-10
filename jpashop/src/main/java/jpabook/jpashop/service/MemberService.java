package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)// 최적화, 읽기전용이니 과부화 x
//@AllArgsConstructor // final의 생성자를 자동생성
@RequiredArgsConstructor // final이 있는 필드를 가지고 생성자를 자동새엉
public class MemberService {

    private final MemberRepository memberRepository; // final추천

//    public MemberService(MemberRepository memberRepository) { // 생성자 1개만있으면 autowired없어도 됨
//        this.memberRepository = memberRepository;
//    }

    // 회원가입
    @Transactional(readOnly = false)// 기본값
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
