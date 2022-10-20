package Hello.core;

import Hello.core.member.Grade;
import Hello.core.member.Member;
import Hello.core.member.MemberService;
import Hello.core.member.MemberServiceImpl;
import Hello.core.order.Order;
import Hello.core.order.OrderService;
import Hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        //main은 컨트롤러라고 생각하면 된다.
        //******=>main = > service 인데 = > service 자체를 appConfig에서 dip로 구현체랑 연결된 객체를 반환해준다
        //멤버에 대한 service
        //AppConfig에서 사용하지 않으면 서비스 에서 구현 객체를 설정해줘야 하므로 DIP 정책 위반
        //MemberService memberService = new MemberServiceImpl(null);
        //AppConfig에서 사용하지 않으면 서비스 에서 구현 객체를 설정해줘야 하므로 DIP 정책 위반
        //OrderService orderService = new OrderServiceImpl(null, null);
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
        Long memberId = 1L;
        //주문하는 인원 세팅
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        //주문 내용
        Order order = orderService.createOrder(memberId, "itamA", 10000);
        System.out.println("order : " + order );
        //할인이 적용된 가격
        System.out.println(order.calculatePrice());

    }
}
