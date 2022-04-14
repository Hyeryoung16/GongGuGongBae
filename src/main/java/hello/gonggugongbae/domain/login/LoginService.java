package hello.gonggugongbae.domain.login;

import hello.gonggugongbae.domain.member.Member;

public interface LoginService {
    public Member login(String loginId, String password);
}
