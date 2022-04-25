package hello.gonggugongbae.app;

import hello.gonggugongbae.config.AutoAppConfig;
import hello.gonggugongbae.domain.location.MyLocation;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = applicationContext.getBean(MemberService.class);

        Member member = new Member("userA", "kim", "1234", MyLocation.GYM_LAT, MyLocation.GYM_LON);
        memberService.join(member);

        Member findMember = memberService.findMemberById(member.getId());
        System.out.println("new member = " + member.getUsername());
        System.out.println("find member = " + findMember.getUsername());
    }
}
