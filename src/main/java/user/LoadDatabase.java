package user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new User ("smb1", 21, "+123456789012", "smb1@gmail.com")));
            log.info("Preloading " + repository.save(new User ("smb2", 42, "+345678901234", "smb2@gmail.com")));
        };
    }
}
