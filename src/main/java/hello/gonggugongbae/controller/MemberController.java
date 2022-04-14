package hello.gonggugongbae.controller;

import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService; // TODO : 방법 맞는지 체크

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

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

    /*
    @PostMapping("/add")
    public String addMember(@Valid @ModelAttribute("member") Member member, BindingResult result) {

        if (result.hasErrors()) {
            return "member/memberAddForm";
        }

        memberService.join(member); // TODO : Save process
        // model.addAttribute("member", member); // @ModelAttribute 에 의해 자동추가 됨
        return "redirect:/";
    }
    */

    @PostMapping("/add")
    public String add(@ModelAttribute("member") Member member,
                      RedirectAttributes redirectAttributes,
                      Model model){
        // 검증 오류를 보관
        Map<String, String> errors = new HashMap<>();

        //검증로직1 : 로그인 아이디 필수
        if(!StringUtils.hasText(member.getLoginId())) {
            errors.put("loginId", "로그인 아이디는 필수 입니다");
        }

        //검증로직2 : 사용자 이름 필수
        if(!StringUtils.hasText(member.getUsername())) {
            errors.put("username", "사용자 이름은 필수 입니다");
        }

        //검증로직3 : 비밀번호 필수
        if(!StringUtils.hasText(member.getPassword())) {
            errors.put("password", "비밀번호는 필수 입니다");
        }

        //검증로직4 : 위도 필수 & 범위는 -90~90 까지 허용
        if(member.getLatitude() == null
                || member.getLatitude() < -90
                || member.getLatitude() > 90) {
            errors.put("latitude", "위도는 -90에서 +90까지 허용합니다");
        }

        //검증로직5 : 경도 필수 & 범위는 -180~180 까지 허용
        if(member.getLongitude() == null
                || member.getLongitude() < -180
                || member.getLongitude() > 180) {
            errors.put("longitude", "경도는 -180에서 +180까지 허용합니다");
        }

        //검증 실패 시, 다시 회원가입 폼으로
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "member/memberAddForm";
        }

        //성공 로직
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, Model model){
        Member member = memberService.findMemberById(memberId);
        model.addAttribute("member", member);
        return "member/memberEditForm";
    }

    @PostMapping("/{memberId}/edit")
    public String editMember(@PathVariable Long memberId, @ModelAttribute("member") Member member){
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
