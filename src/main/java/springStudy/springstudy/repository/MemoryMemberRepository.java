package springStudy.springstudy.repository;

import springStudy.springstudy.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // key값을 생성해주는 변수

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id)); // null이 반환될 가능성이 있으면 Optional.ofNullable을 넣어준다. 그러면 null도 처리가 가능하다(클라이언트가 다른 동작을 취할 수 있다)

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 자바 8이상부터 사용할 수 있는 람다식.
                .findAny();

    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
