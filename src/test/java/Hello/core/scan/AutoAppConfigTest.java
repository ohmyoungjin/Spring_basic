package Hello.core.scan;

import Hello.core.AutoAppConfig;
import Hello.core.discount.DiscountPolicy;
import Hello.core.member.MemberRepository;
import Hello.core.member.MemberService;
import Hello.core.order.OrderService;
import Hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {
    //이슈로인한
    @Test
    void Scan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        DiscountPolicy discountPolicy = bean.getDiscountPolicy();
        System.out.println("memberRepository : " + memberRepository);
        System.out.println("discountPolicy : " + discountPolicy);

    }
}
