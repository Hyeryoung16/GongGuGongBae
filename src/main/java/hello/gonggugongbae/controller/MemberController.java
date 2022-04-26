package hello.gonggugongbae.controller;

import hello.gonggugongbae.argumentresolver.Login;
import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberEditForm;
import hello.gonggugongbae.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService; // TODO : 방법 맞는지 체크

    @GetMapping
    public String member(@Login Member member,
                         Model model){
        model.addAttribute("member",member);
        return "member/member";
    }

    @GetMapping("/edit")
    public String editForm(@Login Member member, Model model){
        model.addAttribute("member", member);
        return "member/memberEditForm";
    }

    @PostMapping("/edit")
    public String editMember(@Login Member member,
                             @Validated @ModelAttribute("member") MemberEditForm form,
                             BindingResult result){
        if (result.hasErrors()) {
            return "member/memberEditForm";
        }

        // 성공로직
        Member editMember = new Member(form.getLoginId(), form.getUsername(), form.getPassword(), form.getLatitude(), form.getLongitude());
        memberService.editMember(member.getId(), editMember);
        return "redirect:/member";
    }

    /* TODO : 테스트용데이터, 이후 삭제 */
    @PostConstruct
    public void init(){
        Location location = new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Member member = new Member("hgd1234", "hong-gil-dong", "pwd1234", MyLocation.GYM_LAT, MyLocation.GYM_LON);
        memberService.join(member);
    }
}
