package user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {

    private @Id @GeneratedValue Long id;

    private String name;
    private int age;
    private String phoneNumber;
    private String email;

    User(){}

    User(String name, int age, String phoneNumber, String email){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
