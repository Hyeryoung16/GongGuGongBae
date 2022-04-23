package hello.gonggugongbae.session;

import hello.gonggugongbae.AppConfig;
import hello.gonggugongbae.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {
    SessionManager sessionManager = new SessionManager(new SessionStoreMemory());

    @Test
    void sessionTest() {
        // given : 세션 생성 + 응답쿠키 저장
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        // when (1) : 세션 조회
        Object result = sessionManager.getSession(request);

        // then (1)
        assertThat(result).isEqualTo(member);

        // when (2) : 세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }
}