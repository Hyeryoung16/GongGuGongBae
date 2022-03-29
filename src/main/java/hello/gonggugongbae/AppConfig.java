package hello.gonggugongbae;

import hello.gonggugongbae.domain.location.LocationPolicy;
import hello.gonggugongbae.domain.location.LocationPolicyImpl;
import hello.gonggugongbae.domain.location.LocationPolicyImplV2;
import hello.gonggugongbae.domain.member.MemberRepository;
import hello.gonggugongbae.domain.member.MemberRepositoryMemory;
import hello.gonggugongbae.domain.member.MemberService;
import hello.gonggugongbae.domain.member.MemberServiceImpl;
import hello.gonggugongbae.domain.party.PartyRepository;
import hello.gonggugongbae.domain.party.PartyRepositoryMemory;
import hello.gonggugongbae.domain.party.PartyService;
import hello.gonggugongbae.domain.party.PartyServiceImpl;

public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    public PartyService partyService(){
        return new PartyServiceImpl(
                memberRepository(),
                partyRepository(),
                locationPolicy()
        );
    }

    public MemberRepository memberRepository(){
        return new MemberRepositoryMemory();
    }

    public PartyRepository partyRepository(){
        return new PartyRepositoryMemory();
    }

    public LocationPolicy locationPolicy() {
        return new LocationPolicyImpl();
    }
}
