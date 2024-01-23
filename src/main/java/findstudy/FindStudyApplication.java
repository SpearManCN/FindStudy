package findstudy;

import findstudy.Config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(SecurityConfig.class)
@SpringBootApplication
public class FindStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindStudyApplication.class, args);
    }

}
