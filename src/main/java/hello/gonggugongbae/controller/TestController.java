package hello.gonggugongbae.controller;

import hello.gonggugongbae.domain.item.Item;
import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberService;
import hello.gonggugongbae.domain.party.Party;
import hello.gonggugongbae.domain.party.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
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

    /* TODO : 테스트용데이터, 이후 삭제 */
    @PostConstruct
    public void init(){
        Member member1 = new Member("id1", "KIM", "1234", MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Member member2 = new Member("id2", "HONG", "1234", MyLocation.PARK_LAT, MyLocation.PARK_LON);
        Member member3 = new Member("id3", "HONG", "1234", MyLocation.STATION_LAT, MyLocation.STATION_LON);
        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);

        Item item1 = new Item("배민-엽떡", "www.hello.com", 8000, 3000);
        Party party1 = new Party(member1.getId(), item1, 3, 30, 3000, new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON));

        Item item2 = new Item("11번가-휴지", "www.world.com", 0, 2500);
        Party party2 = new Party(member1.getId(), item2, 2, 240, 0, new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON));

        Item item3 = new Item("요기요-마라탕", "www.gg-gb.com", 9000, 4000);
        Party party3 = new Party(member2.getId(), item3, 4, 60, 3500, new Location(MyLocation.PARK_LAT, MyLocation.PARK_LON));

        partyService.createParty(party1);
        partyService.createParty(party2);
        partyService.createParty(party3);
    }
}
