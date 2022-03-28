package hello.gonggugongbae.domain.member;

import java.util.List;

public interface MemberRepository {
    void save(Member member); // 저장
    Member findById(Long memberId); // 조회
    List<Member> findAll(); // 전체 조회
}
