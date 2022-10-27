package Hello.core;

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
	}

}
