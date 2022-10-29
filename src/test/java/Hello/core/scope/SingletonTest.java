package Hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        //AnnotationConfigApplicationContext의 파라미터에 들어가게 되면
        //@component 지정이 돼서 @ComponentScan에 등록이 된다.
        //싱글톤은 Spring container가 있을 때 까지 존재한다.
        //싱글톤은  init 후 한 번에 다 같이 생성된다 => singleton 이기 때문에 !
        //하나의 인스턴스를 다 같이 쓴다
        AnnotationConfigApplicationContext ac =  new AnnotationConfigApplicationContext(SingletonBean.class);
        System.out.println("find singletonBean1");
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        System.out.println("find singletonBean2");
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("find singletonBean3");
        SingletonBean singletonBean3 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 : " + singletonBean1);
        System.out.println("singletonBean2 : " + singletonBean2);
        System.out.println("singletonBean3 : " + singletonBean3);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean3);

        ac.close();
    }


    //@Scope의 default 값은 singleton 이기 때문에 굳이 명시를 안해줘도 된다.
    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destory");
        }
    }

}
