package hello.gonggugongbae;

import hello.gonggugongbae.domain.item.Item;
import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberService;
import hello.gonggugongbae.domain.party.Party;
import hello.gonggugongbae.domain.party.PartyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PartyApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        PartyService partyService = applicationContext.getBean("partyService", PartyService.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member("userB", new Location(MyLocation.PARK_LAT, MyLocation.PARK_LON));
        memberService.join(member);

        Item item = new Item("itemA","www.itemlink.com", 8000, 2000);

        Party party = new Party(member.getId(), item, 3, 2, 3000, new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON));
        Party createdParty = partyService.createParty(party);

        System.out.println("createdParty = " + createdParty);
    }
}
