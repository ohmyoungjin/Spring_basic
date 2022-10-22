package Hello.core;

import Hello.core.member.Grade;
import Hello.core.member.Member;
import Hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MemberApp_xml {
    public static void main(String[] args) {
        //컨트롤 개념으로 보면 됨
        //xml 파일 쓰는 부분 =>
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        //appConfig.xml보면 내가 지정할 빈들을 설정해서 가지고 잇다
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member : " + member.getName());
        System.out.println("find member : " + findMember.getName());

    }

}
