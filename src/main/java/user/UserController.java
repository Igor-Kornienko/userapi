package user;

import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final UserRepository repo;
    private final UserResourceAssembler assembler;

    public UserController(UserRepository repo, UserResourceAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    @GetMapping(value = "/users", produces = "application/json")
    Resources<Resource<User>> all(){
        List<Resource<User>> users = repo.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(users,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping(value = "/users", produces = "application/json")
    ResponseEntity<?> newUser(@RequestBody User newUser) throws URISyntaxException {
        Resource<User> res = assembler.toResource(repo.save(newUser));
        return ResponseEntity
                .created(new URI(res.getId().expand().getHref()))
                .body(res);
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    Resource<User> one (@PathVariable Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toResource(user);
    }

    @PutMapping(value = "/users/{id}", produces = "application/json")
    ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) throws URISyntaxException {

        Resource<User> res = assembler.toResource(
                repo.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setAge(newUser.getAge());
                    user.setPhoneNumber(newUser.getPhoneNumber());
                    user.setEmail(newUser.getEmail());
                    return repo.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repo.save(newUser);
                }));

        return ResponseEntity
                .created(new URI(res.getId().expand().getHref()))
                .body(res);
    }

    @DeleteMapping(value = "/users/{id}", produces = "application/json")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        repo.deleteById(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
