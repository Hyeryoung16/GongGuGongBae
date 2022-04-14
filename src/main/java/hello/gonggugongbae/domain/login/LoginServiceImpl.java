package hello.gonggugongbae.domain.login;

import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    private final MemberService memberService;

    @Autowired
    public LoginServiceImpl(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public Member login(String loginId, String password) {
        return memberService.findMemberByLoginId(loginId).filter(member -> member.getPassword().equals(password)).orElse(null); // null 이면 로그인 실패
    }
}
