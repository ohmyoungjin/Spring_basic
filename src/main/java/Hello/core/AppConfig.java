package Hello.core;

import Hello.core.discount.DiscountPolicy;
import Hello.core.discount.FixDiscountPolicy;
import Hello.core.discount.RateDiscountPolicy;
import Hello.core.member.MemberService;
import Hello.core.member.MemberServiceImpl;
import Hello.core.member.MemoryMemberRepository;
import Hello.core.order.OrderService;
import Hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//  구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스
//  동적인 객체 인스턴스 의존 관계를 나타내는 config 파일
//  AppConfig에 설정을 구성한다는 뜻의 @Configuration 을 붙여준다.
//  각 메서드에 @Bean 을 붙여준다. 이렇게 하면 스프링 컨테이너에 스프링 빈으로 등록한다.
//팩토리 메서드로 빈을 등록하는 방법이다 !
@Configuration
public class AppConfig {
//    리팩터링 전
//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//    public OrderService orderService() {
//        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
//     }
//  리팩터링 후 new MemoryMemberRepository() 가 두 번 쓰이는 것을 한 번에 묶어서 해줌
//  얻을 수 있는 이점 : AppConfig 를 보면 역할과 구현 클래스가 한눈에 들어온다. 애플리케이션 전체 구성이 어떻게 되어있는지
//  빠르게 파악할 수 있다.
//  단축키 ctrl + alt + M
//  *****annotation을 달면 DI 컨테이너에서 이 객체를 생성하고 컨테이너에 넣어서 관리하게 된다.
//  @Bean(name="")이름을 명시하면 명시한 이름으로 사용할 수 있지만 이름을 따로 지정하지 않으면
//  메서드 명 ex) memberService로 default저장하게 된다
//    @Bean memberService -> new MemoryMemberRepository()
//    @Bean orderService -> new MemoryMemberRepository()
//    이렇게 총 두 번을 호출하게 되어, 싱글톤이 깨진다고 생각이 될 수도 있다.
//    생각한 흐름
//    call AppConfig.memberService
//    call AppConfig.memberRepository
//    call AppConfig.memberRepository
//    call AppConfig.orderService
//    call AppConfig.memberRepository
//
//    실제 로그
//    call AppConfig.memberService
//    call AppConfig.memberRepository
//    call AppConfig.orderService

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //필드 주입
        //return null;
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }

}
