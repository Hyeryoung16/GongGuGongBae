package hello.gonggugongbae.scan;

import hello.gonggugongbae.config.AutoAppConfig;
import hello.gonggugongbae.domain.member.MemberService;
import hello.gonggugongbae.domain.party.PartyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    void basicScan(){
        MemberService memberService = ac.getBean(MemberService.class);
        PartyService partyService = ac.getBean(PartyService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        Assertions.assertThat(partyService).isInstanceOf(PartyService.class);
    }

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("name = " + beanDefinitionName);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findAllApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName);
            }
        }
    }
}
