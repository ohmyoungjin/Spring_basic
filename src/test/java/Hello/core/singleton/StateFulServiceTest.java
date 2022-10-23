package Hello.core.singleton;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import org.assertj.core.api.Assertions;

class StateFulServiceTest {

    @Test
    void statefulServiceSingeton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);

        //ThreadA : A사용자 10000원 주문
        stateFulService1.order("userA", 10000);
        //ThreadB : B사용자 10000원 주문
        stateFulService2.order("userB", 20000);

        //ThreadA : 사용자 A 주문 금액 조회
        //싱글톤 공유필드 잘못설정하면 문제가 생기는 부분
        //userA에서 만원 주문을 했지만 동시에 userB가 2만원 주문을 하게 되면 공유필드에 값이 2만원이 복사 돼서
        //userA한테도 2만원의 주문서가 가게 된다. 큰 문제다 이건 정말 !
        //int price = stateFulService1.getPrice();
        int userAprice = stateFulService1.order("userA", 10000);
        System.out.println("price : " + userAprice);
        //Assertions.assertThat(stateFulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }
    }
}