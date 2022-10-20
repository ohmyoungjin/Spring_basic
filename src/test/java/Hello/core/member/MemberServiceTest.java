package Hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    private final MemberService memberService ;

    public MemberServiceTest(MemberService memberService) {
        this.memberService = memberService;
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //than
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
