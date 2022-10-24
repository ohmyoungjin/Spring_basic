package Hello.core.singleton;

import Hello.core.AppConfig;
import Hello.core.member.MemberRepository;
import Hello.core.member.MemberServiceImpl;
import Hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        //Appcofig에서 new로 생성해낸 객체들이 총 3번이 있다
        //스프링은 얘네들이 싱글톤이 깨지지 않고 보장해준다
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository : " + memberRepository1);
        System.out.println("orderService -> memberRepository : " + memberRepository2);
        System.out.println("memberRepository -> memberRepository : " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }
}
