package springStudy.springstudy.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import springStudy.springstudy.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 골뱅이 단위의 작업이 끝나고 나면 자동으로 각 골뱅이 뒤에 실행이 되는 것.
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        // Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

    //만약 findAll method의 실행이 먼저 이루어지고 findByName의 실행이 이루어지면 처음 실행됐던 메서드에서 각 객체가 생성이 되기때문에 그 객체로 인해
    //다음 실행된 메서드의 결과값까지 영향을 미친다./
    //따라서 반드시 각 골뱅이 단위의 실행이 끝난 뒤에는 데이터를 클리어해줘야한다.
    //그때 사용하는 것이 afterEach이다.

}
