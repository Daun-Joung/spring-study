package springStudy.springstudy.repository;

import springStudy.springstudy.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name); // Optional 받아오는 데이터의 null을 처리하는 방법
    List<Member> findAll();
}
