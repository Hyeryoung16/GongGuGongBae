package hello.gonggugongbae.controller;

import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.List;


@Controller
@RequestMapping("/gggb/members")
public class MemberController {
    
    @Autowired
    private MemberService memberService; // TODO : 방법 맞는지 체크

    @GetMapping // TODO : 전체 멤버조회는 이후 삭제
    public String members(Model model) {
        List<Member> allMembers = memberService.findAllMembers();
        System.out.println("allMembers = " + allMembers);
        model.addAttribute("members", allMembers);
        return "basic/members";
    }

    @GetMapping("join-form")
    public String newForm(){
        return "new-form";
    }

    @PostMapping("save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("latitude") double lat,
            @RequestParam("longitude") double lon,
            Model model) {
        
        Location location = new Location(lat, lon);
        Member member = new Member(username, location);
        
        memberService.join(member); // TODO : Save process
        
        model.addAttribute("member", member);
        return "save-result";
    }

    /*테스트용데이터, 이후 삭제*/
    @PostConstruct
    public void init(){
        Location location = new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Member member = new Member("userA", location);
        memberService.join(member);
    }
}
