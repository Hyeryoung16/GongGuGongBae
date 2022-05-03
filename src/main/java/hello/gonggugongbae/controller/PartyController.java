package hello.gonggugongbae.controller;

import hello.gonggugongbae.argumentresolver.Login;
import hello.gonggugongbae.domain.item.Item;
import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.party.Party;
import hello.gonggugongbae.domain.party.PartyCreateForm;
import hello.gonggugongbae.domain.party.PartyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/party")
public class PartyController {

    private final PartyService partyService;

    @GetMapping("/show") // TODO : Temp
    public String showParties(Model model) {
        List<Party> parties = partyService.findAllParties();
        model.addAttribute("parties", parties);
        return "party/allParties";
    }

    @GetMapping("/")
    public String myParties(){

        return "party/parties";
    }

    @GetMapping("/add")
    public String addPartyForm(@ModelAttribute Party party){
        return "party/partyAddForm";
    }

    @PostMapping("/add")
    public String addParty(@Login Member member,
                           @Validated @ModelAttribute("party") PartyCreateForm form,
                           BindingResult result){

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                log.info("fieldError = " + fieldError);
            }
            return "party/partyAddForm";
        }

        // 성공로직
        Party party = new Party(member.getId(), form.getItem(), form.getPartyMemberNum(), form.getDuration(), form.getMinOrderPricePerMember(), form.getReceiveLocation());

        Party createdParty = partyService.createParty(party);
        if (createdParty == null) { // 위치 정책상의 실패일 때
            result.reject("LocationRange", new Object[]{member.getLatitude(), member.getLongitude()}, null );
            return "party/partyAddForm";
        }

        log.info("Party Created Success!! ", createdParty.toString());
        return "redirect:/";
    }

    @PostMapping("/participate/{partyId}")
    public String participate(@Login Member member, @PathVariable("partyId") Long partyId){
        log.info("참가 원하는 파티 아디디는 = {}", partyId);
        partyService.participateParty(partyId, member.getId());
        return "redirect:/";
    }
}
