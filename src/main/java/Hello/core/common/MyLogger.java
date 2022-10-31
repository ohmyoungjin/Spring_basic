package Hello.core.common;

import org.springframework.context.annotation.Scope;
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
//그러므로 이 class에 다른 처리를 해주지 않으면
//No thread-bound request found , Error creating bean with name
//이와 같은 error를 뿌리게 된다.
@Component
@Scope(value="request")
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
