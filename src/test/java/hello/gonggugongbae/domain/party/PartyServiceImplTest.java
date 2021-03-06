package hello.gonggugongbae.domain.party;

import hello.gonggugongbae.AutoAppConfig;
import hello.gonggugongbae.domain.item.Item;
import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberService;
import hello.gonggugongbae.domain.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartyServiceImplTest {

    MemberService memberService;
    PartyService partyService;
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @BeforeEach
    public void beforeEach(){
        memberService = ac.getBean(MemberServiceImpl.class);
        partyService = ac.getBean(PartyServiceImpl.class);

        partyService.findAllParties().forEach(party -> partyService.deletePartyById(party.getPartyId()));
    }

    @Test
    @DisplayName("수령장소가 위치정책을 만족하면, 파티 등록 가능")
    void createPartySuccess(){
        // given
        Location receiveLocation = new Location(MyLocation.PARK_LAT, MyLocation.PARK_LON);
        Item item = new Item("itemA", "www.item.com", 9000, 3000);
        Member member = new Member("hello1","username1", "pwd1", MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Member joinedMember = memberService.join(member);
        Party myParty = new Party(joinedMember.getId(), item, 3, 2,
                3000, receiveLocation);

        // when
        Party savedParty = partyService.createParty(myParty);

        // then
        assertThat(savedParty).isNotNull();
        assertThat(savedParty.getMemberId()).isEqualTo(joinedMember.getId());
        assertThat(savedParty.getReceiveLocation()).isEqualTo(receiveLocation);

        //partyService.deletePartyById(joinedMember.getId(), myParty.getPartyId());
        //partyService.findAllParties().forEach(party -> System.out.println("party = " + party));
    }

    @Test
    @DisplayName("수령장소가 위치정책을 위반하면, 파티 등록 불가능")
    void createPartyFail(){
        // given
        Location receiveLocation = new Location(MyLocation.STATION_LAT, MyLocation.STATION_LON);
        Item item = new Item("itemB", "www.item.com", 0, 3000);
        Member member = new Member("hello2", "username2", "pwd", MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Member joinedMember = memberService.join(member);
        Party myParty = new Party(joinedMember.getId(), item, 2, 24,
                0, receiveLocation);

        // when
        Party savedParty = partyService.createParty(myParty);

        // then
        // assertThat(savedParty).isNull();
    }

    @Test
    @DisplayName("회원 주변의 파티 찾기")
    void findPartyAroundMember(){
        // given
        Member memberA = new Member("memberA", "usernameA", "pwdA", MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Member memberB = new Member("memberB",  "usernameA", "pwdA", MyLocation.PARK_LAT, MyLocation.PARK_LON);
        Member memberC = new Member("memberC",  "usernameA", "pwdA", MyLocation.STATION_LAT, MyLocation.STATION_LON);
        memberService.join(memberA);
        memberService.join(memberB);
        memberService.join(memberC);

        Item itemA = new Item("itemA", "www.itemA.com", 0, 3000);
        Party partyA = new Party(memberA.getId(), itemA, 2, 24,
                0, memberA.getAddress());
        Party savedParty = partyService.createParty(partyA);

        // when
        List<Party> partyAroundMemberB = partyService.findPartyAroundMember(memberB.getId());
        List<Party> partyAroundMemberC = partyService.findPartyAroundMember(memberC.getId());

        partyAroundMemberB.forEach(party -> System.out.println("party = " + party));

        // then
        assertThat(partyAroundMemberB.size()).isEqualTo(1);
        // assertThat(partyAroundMemberC.size()).isEqualTo(0);

        //partyService.deletePartyById(memberA.getId(), savedParty.getPartyId());
    }

    @Test
    @DisplayName("팟 참가")
    void participateParty(){
        //given
        Member memberA = new Member("memberA", "usernameA", "pwdA", MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Member memberB = new Member("memberB",  "usernameA", "pwdA", MyLocation.PARK_LAT, MyLocation.PARK_LON);
        memberService.join(memberA);
        memberService.join(memberB);

        Item itemA = new Item("itemA", "www.itemA.com", 0, 3000);
        Party partyA = new Party(memberA.getId(), itemA, 2, 24,
                0, memberA.getAddress());
        Party savedParty = partyService.createParty(partyA);

        //when
        partyService.participateParty(savedParty.getPartyId(), memberB.getId());

        //then
        assertThat(partyService.findPartyById(savedParty.getPartyId()).getPartyMembers().size()).isEqualTo(2);
        assertThat(memberService.findMemberById(memberB.getId()).getParties().size()).isEqualTo(1);

        //partyService.deletePartyById(memberA.getId(), savedParty.getPartyId());
    }
}