package user.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    private @Id @GeneratedValue Long id;

    private @NonNull String name;
    private @NonNull String email;
    private @NonNull String passHash;
    private @NonNull String role;
    private int age;
    private String phoneNumber;
}
