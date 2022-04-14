package hello.gonggugongbae.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member join(Member member);
    Member findMemberById(Long memberId);
    Optional<Member> findMemberByLoginId(String loginId);
    void editMember(Long memberId, Member member);
    List<Member> findAllMembers();
}
