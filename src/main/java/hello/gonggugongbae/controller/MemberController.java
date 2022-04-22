package hello.gonggugongbae.controller;

import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberService;
import hello.gonggugongbae.validation.MemberValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService; // TODO : 방법 맞는지 체크
    //private final MemberValidator memberValidator;

    @GetMapping // TODO : 전체 멤버조회는 이후 삭제
    public String members(Model model) {
        List<Member> allMembers = memberService.findAllMembers();
        model.addAttribute("members", allMembers);
        return "member/members";
    }

    @GetMapping("/{memberId}")
    public String member(@PathVariable Long memberId, Model model){
        Member member = memberService.findMemberById(memberId);
        model.addAttribute("member",member);
        return "member/member";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute Member member){
        return "member/memberAddForm";
    }


    @PostMapping("/add")
    public String addMember(@Validated @ModelAttribute("member") Member member,
                            BindingResult result) {

        /* 특정 필드 예외가 아닌 전체 예외 (Object Error) */

        if (result.hasErrors()) {
            return "member/memberAddForm";
        }

        memberService.join(member); // TODO : Save process
        // model.addAttribute("member", member); // @ModelAttribute 에 의해 자동추가 됨
        return "redirect:/";
    }

    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, Model model){
        Member member = memberService.findMemberById(memberId);
        model.addAttribute("member", member);
        return "member/memberEditForm";
    }

    @PostMapping("/{memberId}/edit")
    public String editMember(@PathVariable Long memberId,
                             @Validated @ModelAttribute("member") Member member,
                             BindingResult result){

        /* 특정 필드 예외가 아닌 전체 예외 (Object Error) */

        if (result.hasErrors()) {
            return "member/memberEditForm";
        }

        memberService.editMember(memberId, member);
        return "redirect:/members/{memberId}";
    }

    /* TODO : 테스트용데이터, 이후 삭제 */
    @PostConstruct
    public void init(){
        Location location = new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Member member = new Member("hgd1234", "hong-gil-dong", "pwd1234", MyLocation.GYM_LAT, MyLocation.GYM_LON);
        memberService.join(member);
    }
}
