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
import hello.gonggugongbae.session.SessionManager;
import hello.gonggugongbae.session.SessionStore;
import hello.gonggugongbae.session.SessionStoreMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public PartyService partyService(){
        return new PartyServiceImpl(
                memberRepository(),
                partyRepository(),
                locationPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemberRepositoryMemory();
    }

    @Bean
    public PartyRepository partyRepository(){
        return new PartyRepositoryMemory();
    }

    @Bean
    public LocationPolicy locationPolicy() {
        return new LocationPolicyImpl(); // 이부분만 이후에 변경하면 된다
        // return new LocationPolicyImplV2();
    }
}
