package hello.gonggugongbae.domain.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepositoryMemory implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 회원 저장소

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    @Override
    public List<Member> findAll() {
        return null; //TODO
    }
}
