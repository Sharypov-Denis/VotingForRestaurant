package den.graduation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;


public class SpringTest {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/spring-db.xml")){
            String str = Arrays.toString(context.getBeanDefinitionNames());
            String [] mas = str.split(",");
            Arrays.stream(mas).forEach(System.out::println);
        }
    }
}
