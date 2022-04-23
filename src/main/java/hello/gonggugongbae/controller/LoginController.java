package hello.gonggugongbae.controller;

import hello.gonggugongbae.domain.login.LoginForm;
import hello.gonggugongbae.domain.login.LoginService;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.session.SessionConst;
import hello.gonggugongbae.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult result,
                        HttpServletRequest request){

        if(result.hasErrors()){
            return "login/loginForm";
        }

        Member member = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        // 로그인 실패 시
        if (member == null){
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다"); // 글로벌 오류 ( Object Error ) 생성
            return "login/loginForm";
        }

        // 로그인 성공 시
        HttpSession session = request.getSession(true); // 세션 있으면 세션 반환, 없으면 새로 생성
        session.setAttribute(SessionConst.LOGIN_MEMBER, member); // 세션에 데이터 보관
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 세션 있으면 세션 반환, 없어도 새로 생성하지 않고 null 반환
        if (session != null) {
            session.invalidate(); // 세션 삭제
        }
        return "redirect:/";
    }
}
