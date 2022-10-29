package Hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        //ApplicationContext(interface) => ConfigurableApplicationContext = > AnnotationConfigApplicationContext
        //spring container 생성
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        //생성자 호출 => 의존 관계 주입 완료 후 callback => NetworkClient.afterPropertiesSet
        //connect() => bean close =>NetworkClient.destroy => close()
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        //@bean에서 제공하는 init , destory를 지정해주면 된다.
        //destroyMethod는 기본 default 값이 (inferred) = 추론 으로 돼 있어서
        //close , shutdown 과 같은 이름을 가진 메서드가 있으면 알아서 호출해준다
        //그래서 밑에 destroyMethod를 따로 적어주지 않았다.
        //destroyMethod="" 으로 하면 추론기능을 사용하지 않아서 아무것도 호출하지 않는다 (disconnect가 안된다)
        @Bean(initMethod = "init")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

}
