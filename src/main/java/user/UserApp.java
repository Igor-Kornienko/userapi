package user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class UserApp {
    public static void main(String ... args) throws IOException {
        SpringApplication.run(UserApp.class, args);
    }
}
