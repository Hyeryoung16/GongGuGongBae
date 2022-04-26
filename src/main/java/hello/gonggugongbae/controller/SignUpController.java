package hello.gonggugongbae.controller;

import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberJoinForm;
import hello.gonggugongbae.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {

    private final MemberService memberService;

    @GetMapping
    public String signUpForm(@ModelAttribute Member member){
        return "member/memberAddForm";
    }

    @PostMapping
    public String signUp(@Validated @ModelAttribute("member")MemberJoinForm form,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "member/memberAddForm";
        }

        // 성공로직
        Member member = new Member(form.getLoginId(), form.getUsername(), form.getPassword(), form.getLatitude(), form.getLongitude());
        memberService.join(member); // TODO : Save process
        return "redirect:/";
    }
}
