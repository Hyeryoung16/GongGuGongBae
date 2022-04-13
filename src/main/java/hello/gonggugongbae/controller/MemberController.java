package hello.gonggugongbae.controller;

import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@Controller
@RequestMapping("/gggb/members")
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
        return "basic/members";
    }

    @GetMapping("/{memberId}")
    public String member(@PathVariable Long memberId, Model model){
        Member member = memberService.findMemberById(memberId);
        model.addAttribute("member",member);
        return "basic/member";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/memberAddForm";
    }

    @PostMapping("/add")
    public String addMember(
            @RequestParam("username") String username,
            @RequestParam("latitude") double lat,
            @RequestParam("longitude") double lon,
            Model model) {
        
        Location location = new Location(lat, lon);
        Member member = new Member(username, location);
        
        memberService.join(member); // TODO : Save process
        
        model.addAttribute("member", member);
        return "basic/member";
    }

    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, Model model){
        Member member = memberService.findMemberById(memberId);
        model.addAttribute("member", member);
        return "basic/memberEditForm";
    }

    @PostMapping("/{memberId}/edit")
    public String editMember(
            @PathVariable Long memberId,
            @RequestParam("username") String username,
            @RequestParam("latitude") double lat,
            @RequestParam("longitude") double lon,
            Model model){
        Member member = new Member(username, new Location(lat, lon));
        memberService.editMember(memberId, member);
        return "redirect:/gggb/members/{memberId}";
    }

    /*테스트용데이터, 이후 삭제*/
    @PostConstruct
    public void init(){
        Location location = new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Member member = new Member("userA", location);
        memberService.join(member);
    }
}
