package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional <User> findUserByName(String Name);

    Boolean existsByName(String Name);

    Boolean existsByEmail(String Email);

    Long countUserByName(String name);
}
