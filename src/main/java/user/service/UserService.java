package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import user.dto.UserDto;
import user.mapper.UserMapper;
import user.model.User;
import user.repository.UserRepository;
import user.exception.UserNotFoundException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<UserDto> all() {
        return UserMapper.INSTANCE.fromUsers(repo.findAll());
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


