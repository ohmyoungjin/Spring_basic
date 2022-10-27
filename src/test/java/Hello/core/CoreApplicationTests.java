package Hello.core;

import Hello.core.member.Grade;
import Hello.core.member.Member;
import Hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

	@Autowired
	MemberService memberService;

	@Test
	void contextLoads() {
		System.out.println("memberService : " + memberService);
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
	}

}
