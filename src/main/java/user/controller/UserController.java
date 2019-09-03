package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import user.service.UserService;

import org.springframework.web.bind.annotation.*;
import user.model.User;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<User> all() {
        return userService.roleFilter(userService.all());
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return userService.roleFilter(userService.one(id));
    }

    @PutMapping("/users/{id}")
    void replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        userService.replaceUser(newUser, id);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
