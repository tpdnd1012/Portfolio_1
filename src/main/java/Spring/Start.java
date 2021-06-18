package Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication // @ 어노테이션 주입 : 해당클래스에게 스프링부트 프로그램 제공
@EnableJpaAuditing // JPA에 감시된 시간값을 넣어줌
public class Start {

    public static void main(String[] args) {

        SpringApplication.run(Start.class, args);

    }

}
