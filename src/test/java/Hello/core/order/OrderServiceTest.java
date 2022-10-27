package Hello.core.order;

import Hello.core.AppConfig;
import Hello.core.AutoAppConfig;
import Hello.core.discount.DiscountPolicy;
import Hello.core.member.*;
import Hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;
class OrderServiceTest {

//    @Autowired MemberService memberService ;
//    @Autowired DiscountPolicy discountPolicy;
    @Autowired MemberRepository memberRepository;

    //OrderService orderService ;
    //@Autowired MemberRepository memberRepository;
    @BeforeEach
    public void beforeEach() {
//        AppConfig appConfig = new AppConfig();
//        memberService = appConfig.memberService();
//        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
//        long memberId = 1L;
//        Member member = new Member(memberId, "memberA", Grade.VIP);
//        memberService.join(member);
//        Order order = orderService.createOrder(memberId, "itemA", 10000);
//        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }


    //필드 주입하게 되면 해당하는 service로직에 값을 저장해줄 수 없으므로
    //service에 다시 setter을 만들어 줘야한다.
    @Test
    void fieldInjectionTest() {
//        long memberId = 1L;
//        Member member = new Member(memberId, "memberA", Grade.VIP);
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
//        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberRepository : "   + memberRepository);
        //memberService.join(member);
//        this.memberService.join(member);
//        orderService.createOrder(memberId, "itemA", 10000);
    }
}