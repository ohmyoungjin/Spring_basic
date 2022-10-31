package Hello.core.web;


import Hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import javax.inject.Provider;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    //  Provider (javax lib를 사용한 모습) => lib를 다운 받아줘야하는 단점이 있다.
    // 대신 spring에 종속되는 것이 아니여서 유연성이 조금 더 높다
     private final Provider<MyLogger> myLoggerProvider;

    //spring 에서 제공하는 ObjectProvider
    //spring container를 사용해야 한다는 단점이 있지만 사용이 편하다.
    //private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String id) {
        MyLogger myLogger = myLoggerProvider.get();
        //MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
