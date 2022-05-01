package hello.gonggugongbae.controller;

import hello.gonggugongbae.argumentresolver.Login;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.party.PartyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PartyService partyService;

    @GetMapping("/")
    public String homeLogin(
            @Login Member member,
            Model model) {

        if (member == null) { return "home"; }

        model.addAttribute("parties", partyService.findPartyAroundMember(member.getId()));
        model.addAttribute("member", member);
        return "loginHome";
    }
}
