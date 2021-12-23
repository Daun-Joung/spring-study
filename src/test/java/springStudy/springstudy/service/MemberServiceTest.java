package springStudy.springstudy.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springStudy.springstudy.domain.Member;
import springStudy.springstudy.repository.MemoryMemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Fail.fail;



class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 각 어노테이션이 실행되기 전에 실행되는 것
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository(); // 우선 memberRepository의 객체를 생성
        memberService = new MemberService(memberRepository); // 그것을 memberService에 매개변수로 전달 그러면 위에 생성된 객체가 memberService에 전달되어 같은 객체를 이야기하는 것 처럼 됨.
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // test는 과감하게 한글로 바꿔도 된다. test는 실제 배포되는 코드가 아니기 때문에 적어도 된다.
        // given - 자료가 주어졌는데
        Member member = new Member();
        member.setName("hello");

        // when - 이것을 실행했을 때
        Long saveId = memberService.join(member);

        // then - 이런 결과가 나와야 돼/  이것이 선생님이 추천하는 기본 작성구조
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    /*
        try{
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}