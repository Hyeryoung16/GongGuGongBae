package hello.gonggugongbae.scan;

import hello.gonggugongbae.AutoAppConfig;
import hello.gonggugongbae.domain.member.MemberService;
import hello.gonggugongbae.domain.member.MemberServiceImpl;
import hello.gonggugongbae.domain.party.PartyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {
    @Test
    void basicScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        PartyService partyService = ac.getBean(PartyService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        Assertions.assertThat(partyService).isInstanceOf(PartyService.class);
    }
}
