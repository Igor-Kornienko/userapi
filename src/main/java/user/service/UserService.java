package user.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import user.database.User;
import user.database.UserRepository;
import user.exceptions.UserNotFoundException;
import java.util.List;

public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> all() {
        List<User> users = repo.findAll();
        return users;
    }

    public void newUser(User newUser) {
        newUser.setPassHash(new BCryptPasswordEncoder().encode(newUser.getPassHash()));
        repo.save(newUser);
    }

    public User one(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void replaceUser(User newUser, Long id) {
        repo.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setPassHash(new BCryptPasswordEncoder().encode(newUser.getPassHash()));
                    user.setRole(newUser.getRole());
                    user.setAge(newUser.getAge());
                    user.setPhoneNumber(newUser.getPhoneNumber());
                    return repo.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repo.save(newUser);
                });
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}


