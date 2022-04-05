package hello.gonggugongbae;

import hello.gonggugongbae.domain.member.MemberService;
import hello.gonggugongbae.domain.party.PartyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PartyApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        PartyService partyService = applicationContext.getBean("partyService", PartyService.class);

    }
}
