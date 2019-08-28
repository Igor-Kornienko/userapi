package user.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import user.database.User;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {
    @Override
    public Resource<User> toResource(User user) {
        return new Resource<>(user,
                ControllerLinkBuilder.linkTo(methodOn(UserService.class).one(user.getId())).withSelfRel(),
                linkTo(methodOn(UserService.class).all()).withRel("users"));
    }
}
