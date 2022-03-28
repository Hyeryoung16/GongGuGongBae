package hello.gonggugongbae.domain.member;

import java.util.List;

public class MemberServiceImpl implements MemberService{

    MemberRepository memberRepository = new MemberRepositoryMemory(); // 회원 저장소 객체

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public List<Member> findAllMembers() {
        return null; // TODO
    }
}
