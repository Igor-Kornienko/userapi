package user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@EnableJpaRepositories(basePackages = {"user.repository"})
@SpringBootApplication
public class UserApp {
    public static void main(String ... args) throws IOException {
        SpringApplication.run(UserApp.class, args);
    }
}
