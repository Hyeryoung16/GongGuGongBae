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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostConstruct void init(){
        Item item1 = new Item("배민-엽떡", "www.hello.com", 8000, 3000);
        Party party1 = new Party(1L, item1, 3, 30, 3000, new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON));

        Item item2 = new Item("11번가-휴지", "www.world.com", 0, 2500);
        Party party2 = new Party(1L, item2, 2, 240, 0, new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON));

        Item item3 = new Item("요기요-마라탕", "www.gg-gb.com", 9000, 4000);
        Party party3 = new Party(2L, item3, 4, 60, 3500, new Location(MyLocation.PARK_LAT, MyLocation.PARK_LON));

        partyService.createParty(party1);
        partyService.createParty(party2);
        partyService.createParty(party3);
    }
}
