package Hello.core.discount;

import Hello.core.member.Grade;
import Hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//이 class의 타입은 DiscountPolicy가 된다
//DiscountPolicy의 구현체는 FixDiscountPolicy
@Component
@Primary
public class FixDiscountPolicy implements  DiscountPolicy{
//  discount policy를 의존하고 있다 ! 구현체 이기 때문에 !

    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        return 0;
    }
}
