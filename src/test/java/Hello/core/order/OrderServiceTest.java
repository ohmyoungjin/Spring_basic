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
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    OrderService orderService ;
    MemberService memberService;
    MemberRepository memberRepository;
    @BeforeEach
    public void beforeEach() {
        //싱글톤 방식으로 사용하기
        //java 쪽에서 new 개념으로 만드는거라 싱글톤이 유지되지 못한다.
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(2000);
    }


    //필드 주입하게 되면 해당하는 service로직에 값을 저장해줄 수 없으므로
    //service에 다시 setter을 만들어 줘야한다.
    //필드 주입 후 하게 되면 필요한 구현체를 DI를 못 받기 때문에 spring container가 없게 되면 테스트 자체가 불가능하다.
    @Test
    void fieldInjectionTest() {
//        long memberId = 1L;
//        Member member = new Member(memberId, "memberA", Grade.VIP);
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        OrderService orderService =ac.getBean("orderService", OrderService.class);
//        MemberService memberService =ac.getBean("memberService", MemberService.class);
        System.out.println("memberService : "   + memberService);
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        //orderService.createOrder(memberId, "itemA", 10000);
        //memberService.join(member);
//        this.memberService.join(member);
//        orderService.createOrder(memberId, "itemA", 10000);
    }
}