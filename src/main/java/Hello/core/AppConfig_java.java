package Hello.core;

import Hello.core.discount.DiscountPolicy;
import Hello.core.discount.RateDiscountPolicy;
import Hello.core.member.MemberService;
import Hello.core.member.MemberServiceImpl;
import Hello.core.member.MemoryMemberRepository;
import Hello.core.order.OrderService;
import Hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스
//동적인 객체 인스턴스 의존 관계를 나타내는 config 파일
public class AppConfig_java {
//    리팩터링 전
//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//    public OrderService orderService() {
//        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
//     }
//리팩터링 후 new MemoryMemberRepository() 가 두 번 쓰이는 것을 한 번에 묶어서 해줌
//  얻을 수 있는 이점 : AppConfig 를 보면 역할과 구현 클래스가 한눈에 들어온다. 애플리케이션 전체 구성이 어떻게 되어있는지
//    빠르게 파악할 수 있다.
//  단축키 ctrl + alt + M
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //return null;
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
