package user.database;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {

    private @Id @GeneratedValue Long id;

    private @NonNull String name;
    private @NonNull String email;
    private @NonNull String passHash;
    private int age;
    private String phoneNumber;

    public User(){}

    public User(String name, int age, String phoneNumber, String email){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
