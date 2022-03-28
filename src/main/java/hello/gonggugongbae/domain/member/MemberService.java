package hello.gonggugongbae.domain.member;

import java.util.List;

public interface MemberService {
    void join(Member member);
    Member findMemberById(Long memberId);
    List<Member> findAllMembers();
}
