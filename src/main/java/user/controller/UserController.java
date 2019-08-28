package user.controller;

import user.service.UserService;

import org.springframework.web.bind.annotation.*;
import user.database.User;
import user.database.UserRepository;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserRepository repo) {
        userService = new UserService(repo);
    }

    @GetMapping(value = "users", produces = "application/json")
    List<User> all() {
        return userService.all();
    }

    @PostMapping(value = "users", produces = "application/json")
    void newUser(@RequestBody User newUser) {
        userService.newUser(newUser);
    }

    @GetMapping(value = "users/{id}", produces = "application/json")
    User one(@PathVariable Long id) {
        return userService.one(id);
    }

    @PutMapping(value = "users/{id}", produces = "application/json")
    void replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        userService.replaceUser(newUser, id);
    }

    @DeleteMapping(value = "users/{id}", produces = "application/json")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
