package springStudy.springstudy.service;

import springStudy.springstudy.domain.Member;
import springStudy.springstudy.repository.MemberRepository;
import springStudy.springstudy.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    // 클래스에서 ctrl + shift + t를 누르면 test케이스의 껍데기를 자동으로 생성하는 기능이 있다.
    // getter, setter 만드는 것과 비슷한 느낌.

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){

        //같은 이름이 있는 중복회원 x
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // member의 이름을 받아와서
                .ifPresent(m -> { // 만약 있으면
                    throw new IllegalStateException("이미 존재하는 회원입니다."); // 존재하는 회원입니다를 실행해라.
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
