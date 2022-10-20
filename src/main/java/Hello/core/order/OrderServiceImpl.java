package Hello.core.order;

import Hello.core.discount.DiscountPolicy;
import Hello.core.member.Member;
import Hello.core.member.MemberRepository;
import Hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements  OrderService{
    //***service에서 필요한 구현체들을 선언만 해두고
    //생성자로 주입해준다 !!! ***** 중요
    private final MemberRepository memberRepository ;

    //1000원 할일
    //private DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //10%할인 이 부분으로 변경하는 순간 service class file도 변경되기 때문에 OCP에 위반된다.
    //service가 구현체도 의존하게 됨. => DIP위반 추상에만 의존하도록 변경(인터페이스에만 의존하도록)
    //***
    //private DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //변경 => DI가 이루어지지 않은 상태에서 돌리면 NPE (null pointer execption발생)
    //private DiscountPolicy discountPolicy;
    //해결 방안 => 누군가가 클라이언트인 OrderServiceImpl 에게 discountPolicy의 구현객체(imp)를 대신 생성하고 주입해줘야 한다. (DI)
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
