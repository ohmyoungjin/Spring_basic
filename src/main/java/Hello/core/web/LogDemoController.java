package Hello.core.web;

import Hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


//@RequiredArgsConstructor는 lombok lib를 사용하여 생성자를 만드는 부분
@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURI().toString();
        //Http url로 온 request 정보를 myLogger class의
        //setRequestUrl function에 담는다
        //myLogger class에는 @PostConstruct가 존재하기 때문에 호출과 동시에 난수를 생성한다
        myLogger.setRequestUrl(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";

    }
}
