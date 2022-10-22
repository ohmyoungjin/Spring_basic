package Hello.core.discount;

import Hello.core.member.Grade;
import Hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{
//  discount policy를 의존하고 있다 ! 구현체 이기 때문에 !
    private int discountPercent = 20;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent /100;
        } else {
            return 0;
        }
    }
}
