package hello.gonggugongbae;

import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member("userA", new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON));
        memberService.join(member);

        Member findMember = memberService.findMemberById(member.getId());
        System.out.println("new member = " + member.getUsername());
        System.out.println("find member = " + findMember.getUsername());
    }
}
