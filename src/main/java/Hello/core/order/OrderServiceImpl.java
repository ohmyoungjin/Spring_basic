package Hello.core.order;

import Hello.core.discount.DiscountPolicy;
import Hello.core.member.Member;
import Hello.core.member.MemberRepository;
import Hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements  OrderService{

    private MemberRepository memberRepository = new MemoryMemberRepository();
    //1000원 할일
    //private DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //10%할인 이 부분으로 변경하는 순간 service class file도 변경되기 때문에 OCP에 위반된다.
    //service가 구현체도 의존하게 됨. => DIP위반 추상에만 의존하도록 변경(인터페이스에만 의존하도록)
    //private DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //변경
    private DiscountPolicy discountPolicy;


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
