package hello.gonggugongbae.domain.member;

import hello.gonggugongbae.domain.location.Location;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @AfterEach
    void afterEach() {
        memberService.
    }

    @Test
    void join(){
        // given
        Location location = new Location(37.5105381278854, 126.7608340028786);
        Member member = new Member("memberA", location);

        // when
        Member savedMember = memberService.join(member);

        // then
        Member findMember = memberService.findMemberById(member.getId());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void findAll(){
        // given
        Location location1 = new Location(37.5105381278854, 126.7608340028786);
        Member member1 = new Member("memberA", location1);
        Location location2 = new Location(37.50951356754758, 126.76025559285334);
        Member member2 = new Member("memberB", location2);



    }

}