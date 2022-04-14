package hello.gonggugongbae.domain.member;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepositoryMemory implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 회원 저장소
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream().filter(member -> member.getLoginId().equals(loginId)).findFirst();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long memberId, Member member) {
        Member findMember = store.get(memberId);
        findMember.setUsername(member.getUsername());
        findMember.setPassword(member.getPassword());
        findMember.setLatitude(member.getLatitude());
        findMember.setLongitude(member.getLongitude());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
