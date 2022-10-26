package Hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class, ()->
            ac.getBean("beanB", BeanB.class));
    }
    //
    @Configuration
    //내가 직접 만든 컴포넌트를 스캔한다.
    //Hello.core.scan.filter 여기까지 component들을 다 스캔한다.
    @ComponentScan(
            //이 컴포넌트들을 추가 하겠다는 부분
            //type = FilterType.ANNOTATION defalut값이므로 빼도 된다.
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            //이 컴포넌트들은 빼겠다는 부분
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)

    )
    static class ComponentFilterAppConfig{

    }
}
