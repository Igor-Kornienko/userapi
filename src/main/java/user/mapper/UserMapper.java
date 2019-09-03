package user.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import user.dto.RoleDto;
import user.dto.UserDto;
import user.model.Role;
import user.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserDto userDto);

    List<User> toUsers(List<UserDto> userDto);

    @InheritInverseConfiguration
    UserDto fromUser(User user);

    @InheritInverseConfiguration
    List<UserDto> fromUsers(List<User> user);

    void updateUserFromDto(UserDto userDto, @MappingTarget User user);

    void updateUsersFromDtos(List<UserDto> userDto, @MappingTarget List<User> user);

    default List<RoleDto> roleToRoleDto (List<Role> role) {
        return RoleMapper.INSTANCE.fromRoles(role);
    }
}
