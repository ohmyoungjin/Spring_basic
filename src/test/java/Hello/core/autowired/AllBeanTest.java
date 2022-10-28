package Hello.core.autowired;

import Hello.core.AutoAppConfig;
import Hello.core.discount.DiscountPolicy;
import Hello.core.member.Grade;
import Hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
//      밑과 같이 선언을 하게 되면 DiscountService는 구현체(FixDiscountPolicy, rateDiscountPolicy를 주입받지 못한 상태이므로
//      주입 해줄 수 있는 AutoAppconfig를 같이 선언해준다 (ComponentScan을 이용해 spring container에 bean을 올려둠)
//        ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class);
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPolicy = discountService.discount(member, 10000, "rateDiscountPolicy");
        Assertions.assertThat(rateDiscountPolicy).isEqualTo(2000);


    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int i, String discountCode) {
            // 맵에는 현재
            // 어떤 구현체를 쓸 건지 매개변수로 받아서 사용하는 로직이다.
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, i);
        }

    }

}
