package Hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    //prototype은 호출 될 때 마다 새로 생성하기 때문에 밑에 1 , 2 , 3 주소 값이 다 다르다
    @Test
    void prototypeBeanFind() {

        AnnotationConfigApplicationContext ac =  new AnnotationConfigApplicationContext(PrototypeTest.prototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeTest.prototypeBean prototypeBean1 = ac.getBean(PrototypeTest.prototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeTest.prototypeBean prototypeBean2 = ac.getBean(PrototypeTest.prototypeBean.class);
        System.out.println("find prototypeBean3");
        PrototypeTest.prototypeBean prototypeBean3 = ac.getBean(PrototypeTest.prototypeBean.class);
        System.out.println("prototypeBean1 : " + prototypeBean1);
        System.out.println("prototypeBean2 : " + prototypeBean2);
        System.out.println("prototypeBean3 : " + prototypeBean3);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean3);

        ac.close();
    }

    @Scope("prototype")
    static class prototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("prototype.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototype.destory");
        }
    }

}
