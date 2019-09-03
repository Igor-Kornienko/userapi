package user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    private @Id @GeneratedValue Long id;

    private @NonNull @Size(max = 100) String name;
    private @NonNull @Size(max = 100) @Email String email;
    private @NonNull @Size(max = 60) String passHash;
    private int age;
    private @Size(max = 15) String phoneNumber;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "UserRoles",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roles = new ArrayList<>();

    public User (String name, String email, String passHash) {
        this.name = name;
        this.email = email;
        this.passHash = passHash;
    }
}
