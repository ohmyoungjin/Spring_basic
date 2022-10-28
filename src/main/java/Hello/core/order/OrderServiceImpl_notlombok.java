package Hello.core.order;

import Hello.core.discount.DiscountPolicy;
import Hello.core.member.Member;
import Hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl_notlombok implements  OrderService{
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
    //필드 주입
    //지금은 잘 안쓰게 된다.
    // 테스트 용도로 외부에서 이 repository를 변경할 수 있는 방법이 전혀 없다 쓰지말자.
//    @Autowired private DiscountPolicy discountPolicy;

//    @Autowired private MemberRepository memberRepository ;
    //생성자 주입 생성자가 한개면 @Autowired를 안써도 된다.
    @Autowired
    public OrderServiceImpl_notlombok(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        System.out.println("1111");
        System.out.println("memberId : " + memberId);
        Member member = memberRepository.findById(memberId);
        System.out.println("22222");
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

//    setter주입
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
