package user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role {

    private @Id @GeneratedValue Long id;
    private @NonNull String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<User>();
}
