package hello.gonggugongbae.domain.member;

import java.util.List;

public interface MemberService {
    Member join(Member member);
    Member findMemberById(Long memberId);
    void editMember(Long memberId, Member member);
    List<Member> findAllMembers();
}
