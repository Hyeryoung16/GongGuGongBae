package hello.gonggugongbae.domain.party;

import hello.gonggugongbae.AppConfig;
import hello.gonggugongbae.domain.item.Item;
import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberService;
import hello.gonggugongbae.domain.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PartyServiceImplTest {

    MemberService memberService;
    PartyService partyService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        partyService = appConfig.partyService();
    }

    @Test
    @DisplayName("수령장소가 위치정책을 만족하면, 파티 등록 가능")
    void createPartySuccess(){
        // when
        Location receiveLocation = new Location(MyLocation.PARK_LAT, MyLocation.PARK_LON);
        Item item = new Item("itemA", "www.item.com", 9000, 3000);
        Member member = new Member("hello1","username1", "pwd1", MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Member joinedMember = memberService.join(member);
        Party myParty = new Party(joinedMember.getId(), item, 3, 2,
                3000, receiveLocation);

        // then
        Party savedParty = partyService.createParty(myParty);

        // when
        assertThat(savedParty).isNotNull();
        assertThat(savedParty.getMemberId()).isEqualTo(joinedMember.getId());
        assertThat(savedParty.getReceiveLocation()).isEqualTo(receiveLocation);

        partyService.deletePartyById(joinedMember.getId(), myParty.getPartyId());
    }

    @Test
    @DisplayName("수령장소가 위치정책을 위반하면, 파티 등록 불가능")
    void createPartyFail(){
        // when
        Location receiveLocation = new Location(MyLocation.STATION_LAT, MyLocation.STATION_LON);
        Item item = new Item("itemB", "www.item.com", 0, 3000);
        Member member = new Member("hello2", "username2", "pwd", MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Member joinedMember = memberService.join(member);
        Party myParty = new Party(joinedMember.getId(), item, 2, 24,
                0, receiveLocation);

        // then
        Party savedParty = partyService.createParty(myParty);

        // when
        assertThat(savedParty).isNull();
    }

    @Test
    @DisplayName("회원 주변의 파티 찾기")
    void findPartyAroundMember(){
        // when
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

        // then
        assertThat(partyAroundMemberB.size()).isEqualTo(1);
        assertThat(partyAroundMemberC.size()).isEqualTo(0);

        partyService.deletePartyById(memberA.getId(), savedParty.getPartyId());
    }
}