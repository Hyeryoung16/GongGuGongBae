package hello.gonggugongbae.domain.member;

import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = new MemberRepositoryMemory();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("memberRepository member 저장 테스트")
    void save(){
        // given
        Member member = new Member("memberA", "usernameA", "pwdA", MyLocation.GYM_LAT, MyLocation.GYM_LON);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    @DisplayName("memberRepository 모든 member 찾기 테스트")
    void findAll(){
        // given
        Member member1 = new Member("memberA", "usernameA", "pwdA", MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Member member2 = new Member("memberB", "usernameB", "pwdB", MyLocation.PARK_LAT, MyLocation.PARK_LON);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }

}