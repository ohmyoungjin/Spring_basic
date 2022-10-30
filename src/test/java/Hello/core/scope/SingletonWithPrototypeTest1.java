package Hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        //이런식으로 bean 자체가 달라지면 singleton에서도 관리하는 bean 이 다르므로 다른 객체이다.
//        AnnotationConfigApplicationContext ac
//                = new AnnotationConfigApplicationContext(ClientBean.class, ClientBean2.class, PrototypeBean.class);
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        System.out.println("count1 : " + count1);
        Assertions.assertThat(count1).isEqualTo(1);


        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        System.out.println("count2 : " + count2);
        Assertions.assertThat(count2).isEqualTo(1);
    }

//    @Scope("singleton")
//    static class ClientBean {
//        //생성시점에 주입
//        private final PrototypeBean prototypeBean;
//        //생성자가 한 개 이기때문에 생략 가능 , 롬복으로 처리도 가능
//        //Singleton bean 안에서 prototype bean 을 호출하게 되면 처음 한 번만 가지고 있는다
//        //=>원래 prototype bean으로 호출 하면 호출 될 때 마다 객체가 생성되어야 하지만
//        //singleton bean 안에서 호출되게 되면 처음 singleton bean이 생성되는 그 시기 한 번에만 호출되어
//        //주소값 (참조값)을 가지고 있는다
//        //그래서 위와 같이 singletonClientUsePrototype 에서 호출 될 때 마다 ++되어서 값이 계속 증가하게 된다
//        //singleton bean 안에서 호출되는 prototype bean이 아닌 prototype bean만 호출되게 되면
//        //객체가 계속 생성하게 되어 1으로 고정 되지만 singleton bean안에서는 처음 한 번만 호출된다.
//        //provider를 사용하면 이와 같은 문제점을 해결할 수 있다
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
//
//        public int logic() {
//            prototypeBean.addCount();
//            return prototypeBean.getCount();
//        }
//    }


    @Scope("singleton")
    static class ClientBean {
        //생성시점에 주입
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;


        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }
//    @Scope("singleton")
//    static class ClientBean2 {
//        //생성시점에 주입
//        private final PrototypeBean prototypeBean;
//        @Autowired
//        public ClientBean2(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
//
//        public int logic() {
//            prototypeBean.addCount();
//            return prototypeBean.getCount();
//        }
//    }
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init : " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy : " + this);
        }
    }
}
