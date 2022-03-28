package hello.gonggugongbae.domain.member;

import java.util.List;

public interface MemberRepository {
    Member save(Member member); // 저장
    Member findById(Long memberId); // 조회
    List<Member> findAll(); // 전체 조회
    void clearStore();
}
