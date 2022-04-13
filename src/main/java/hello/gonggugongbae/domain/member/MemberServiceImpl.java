package hello.gonggugongbae.domain.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    // MemberRepository memberRepository = new MemberRepositoryMemory(); // 회원 저장소 객체
    MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public void editMember(Long memberId, Member member) {
        memberRepository.update(memberId, member);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
