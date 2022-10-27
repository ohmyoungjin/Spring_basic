package Hello.core;

import Hello.core.member.MemberRepository;
import Hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//설정 파일임을 명시하는 Annotation => 객체 생성 후 singleton으로 유지시켜줌 (CGLIB)
@Configuration
//@ComponentScan은 이름 그대로 @Component annotation이 붙은 클래스를 스프링 빈으로 등록한다.
@ComponentScan(
        //컴포넌트 스캔할 패키지를 설정한다 밑에 기재된 하위 패키지 및 클래스들만 찾도록 지정한다.
        //이렇게 지정해줘야지 범위를 줄여서 컴파일 되는 시간을 줄인다.
        basePackages = "Hello.core.member",
        //default값은 해당 ComponentScan이 지정된 ex)Hello.core 하위 패키지만 찾는다.
        //지금 구조를 보면 Hello.core바로 밑에 ComponentScan을 쓰게 될 설정파일을 만들어놓는다.
        //참고로 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication 를 이
        //프로젝트 시작 루트 위치에 두는 것이 관례이다
        //이 class에서 제일 위의 패키지까지만 찾도록 한다.
        basePackageClasses = AutoAppConfig.class,
        //scan에서 제외할 빈을 명시한다.
        //Configuration(Appconfig)은 수동으로 등록되는 하는 파일이여서 자동으로 등록되면 안된다.
//        컴포넌트 스캔을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에,
//        AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고, 실행되어 버린다. 그래서
//        excludeFilters 를 이용해서 설정정보는 컴포넌트 스캔 대상에서 제외했다. 보통 설정 정보를 컴포넌트
//        스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택했다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    //빈 충돌
    //수동 빈 등록 VS 자동 빈 등록 끼리 충돌이 나게 되면
    //수동 빈 등록이 우선권을 가지게 되어 override가 발생된다.
    //하지만 개발자 의도와는 상관없이 설정이 꼬이는 경우가 있어서 충돌이 나게되면 exception을 내린다
    //application.properties에 setting spring.main.allow-bean-definition-overriding=true값으로 설정하면
    //Exception을 떨어뜨리지 않는다.
    @Bean
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
