package hello.gonggugongbae.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 저장
    Member findById(Long memberId); // 회원 식별자로 조회
    Optional<Member> findByLoginId(String loginId); // 로그인 아이디로 조회
    List<Member> findAll(); // 전체 조회
    void update(Long memberId, Member member); // 수정
    void clearStore();
}
