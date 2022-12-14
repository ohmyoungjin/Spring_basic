package Hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

//value 는 명시안해도 된다.
//Scope이 request이기 때문에 request 들어오는 시점에 생성되고
//http요청이 끝나는 시점(response)에서 소멸된다.
//requestURL 은 이 빈이 생성되는 시점에는 알 수 없으므로, 외부에서 setter로 입력 받는다
//controller , service 에서 MyLogger을 의존성 주입을 받아야 하는데
//MyLogger Scope = request 이기 때문에 request를 받지 않으면 아직 이 bean은 생성되지 않았다.
//그러므로 이 class를 주입 받는 class에서 다른 처리를 해주지 않으면
//No thread-bound request found , Error creating bean with name
//이와 같은 error를 뿌리게 된다.
//proxyMode = ScopedProxyMode.TARGET_CLASS
//이 조건을 주게 되면
//CGLIB 에서 해당하는 빈을 가짜로 만들어 놓은 후 (이 전에 배웠던 CGLIB 바이트 코드로 만들어놓는 느낌)
//myLogger = class Hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$ff65c64b 이렇게 생성 됨
//먼저 주입해준 후 그다음에 진짜로 request가 와서 bean이 생성되면 그 때 진짜 bean을 주입해준다 !
@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestUrl(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : " + this );
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close : " + this );
    }
}
