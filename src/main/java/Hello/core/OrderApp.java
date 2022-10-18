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
        //멤버에 대한 service
        MemberService memberService = new MemberServiceImpl();
        //주문에 대한 service
        OrderService orderService = new OrderServiceImpl();

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
