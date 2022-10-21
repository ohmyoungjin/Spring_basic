package Hello.core;

import Hello.core.member.*;

public class MemberApp {
    public static void main(String[] args) {
        //컨트롤 개념으로 보면 됨

        //이게 DI개념이 들어가지 않은 부분
        //MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());

        //Di개념이 적용된 부분
        //AppConfig에서 DI로 memverService 객체를 반환해준다
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member : " + member.getName());
        System.out.println("find member : " + findMember.getName());

    }

}
