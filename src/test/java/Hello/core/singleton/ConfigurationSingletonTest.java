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

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

//      결과 값 : bean : class Hello.core.AppConfig$$EnhancerBySpringCGLIB$$8814cd10
        System.out.println("bean : " + bean.getClass());
//      순수한 클래스라면 class hello.core.AppConfig 이와 같이 나와야 한다.
//      이와 같이 GBLIB에서 해당하는 빈이 스프링 컨테이너에 등록되어 있으면 등록된 빈을 반환해주고
//      없으면 만들어서 반환해준다 = > 이로 인해 싱글톤이 보장된다.


    }
}
