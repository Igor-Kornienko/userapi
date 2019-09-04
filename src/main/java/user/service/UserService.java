package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.dto.UserDto;
import user.mapper.UserMapper;
import user.model.User;
import user.repository.UserRepository;
import user.exception.UserNotFoundException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public void create1hundredthousandsusers() {

        Date startTime = new Date();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable create20000user = () -> {
            List<User> users = new ArrayList<>();

            for (int i = 0; i < 20000; i++) {
                User a = new User();
                a.setName("a");
                a.setEmail("a@gmail.com");

                users.add(a);
            }

            repo.saveAll(users);

            System.out.println(new Date().getTime() - startTime.getTime());
        };

        for (int i = 0; i < 5; i++) {
            executor.execute(create20000user);
        }

        executor.shutdown();
    }

    public long count(){
        return repo.countUserByName("a");
    }

    public List<UserDto> all() {
        return repo.findAll().stream()
                .map(user -> UserMapper.INSTANCE.fromUser(user))
                .collect(Collectors.toList());
    }

    public UserDto one(Long id) {
        return UserMapper.INSTANCE.fromUser(repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    public void replaceUser(User newUser, Long id) {
        repo.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setPassHash(new BCryptPasswordEncoder().encode(newUser.getPassHash()));
                    user.setRoles(newUser.getRoles());
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


