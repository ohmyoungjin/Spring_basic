package Hello.core;

import Hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //컨트롤 개념으로 보면 됨
        //스프링 컨테이너를 쓰는 부분
        //스프링 컨테이너 생성 (어노테이션 기반 자바 코드 설정 사용) AppCofing.class를 파라미터로 받아서 안에
        //지정해놓은 Bean들을 담아서 스프링 컨테이너가 들고 있다 !
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member : " + member.getName());
        System.out.println("find member : " + findMember.getName());

    }

}
