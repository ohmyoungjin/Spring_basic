package Hello.core.order;

import Hello.core.AppConfig;
import Hello.core.discount.DiscountPolicy;
import Hello.core.discount.FixDiscountPolicy;
import Hello.core.member.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
//순수한 자바 테스트
class OrderServiceImplTest {

    @Test
    void createOrder() {
        //싱글톤을 사용하지 않은 appconfig를 호출해서 사용하는 방법
//        AppConfig appconfig = new AppConfig();
//        MemberRepository memberRepository = appconfig.memberRepository();
//        MemberService memberService = appconfig.memberService();
//        DiscountPolicy discountPolicy = appconfig.discountPolicy();
//        Member member = new Member(1L, "itemA", Grade.VIP);
//        memberService.join(member);
//      싱글톤 사용 Spring container를 사용한 방법
//      ApplicationContext 이 구문자체가 spring Container를 만든다고 생각하면 된다.
//      ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//      MemberRepository memberRepository  = ac.getBean("memberRepository", MemberRepository.class);
//      MemberService memberService = ac.getBean("memberService", MemberService.class);
//      DiscountPolicy discountPolicy = ac.getBean("discountPolicy", DiscountPolicy.class);
//      Member member = new Member(1L, "itemA", Grade.VIP);
//      memberService.join(member);
        //
        // OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, discountPolicy);


        //bean을 사용하지 않은 구현체를 직접 넣어서 구현도 가능하다 !
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "itemA", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        orderService.createOrder(1L, "itemA", 10000);
    }
}