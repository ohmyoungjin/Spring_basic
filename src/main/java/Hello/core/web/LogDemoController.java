package Hello.core.web;

import Hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;


//@RequiredArgsConstructor는 lombok lib를 사용하여 생성자를 만드는 부분
@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    //MyLogger은 Scope가 request이기 때문에 요청이 올 때 까지는 주입을 받지 못한다.
    //request가 올 때 생성하는 Provider를 사용해서 처리한다.

    private final Provider<MyLogger> myLoggerProvider;

    //private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURI().toString();
        MyLogger myLogger = myLoggerProvider.get();
        //MyLogger myLogger = myLoggerProvider.getObject();
        //Http url로 온 request 정보를 myLogger class의
        //setRequestUrl function에 담는다
        //myLogger class에는 @PostConstruct가 존재하기 때문에 호출과 동시에 난수를 생성한다
        myLogger.setRequestUrl(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";

    }
}
