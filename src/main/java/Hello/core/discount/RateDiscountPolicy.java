package Hello.core.discount;

import Hello.core.member.Grade;
import Hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//동일한 타입이 둘 이상있을 시 구분해야 하는 경우 @Qualifier를 사용해 구분자를 만들어준다
@Primary
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
