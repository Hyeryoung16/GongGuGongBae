package hello.gonggugongbae.controller;

import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberService;
import hello.gonggugongbae.domain.party.Party;
import hello.gonggugongbae.domain.party.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class TestController {

    private final PartyService partyService;
    private final MemberService memberService;

    @GetMapping("/allParties")
    public List<Party> getPartiesInfo() {
        List<Party> allParties = partyService.findAllParties();
        return allParties;
    }

    @GetMapping("/allMembers")
    public List<Member> getMembersInfo(){
        List<Member> allMembers = memberService.findAllMembers();
        return allMembers;
    }
}
