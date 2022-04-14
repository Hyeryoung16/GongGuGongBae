package hello.gonggugongbae.controller;

import hello.gonggugongbae.domain.login.LoginForm;
import hello.gonggugongbae.domain.login.LoginService;
import hello.gonggugongbae.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String loginForm(){
        return "login/loginForm";
    }

    @PostMapping("/")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult result){

        if(result.hasErrors()){
            return "login/loginForm";
        }

        Member member = loginService.login(form.getLoginId(), form.getPassword());

        if (member == null){
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        return "redirect:/members"; // 로그인 성공 시
    }
}
