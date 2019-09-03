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
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto fromUser(User user);

    void updateUserFromDto(UserDto userDto, @MappingTarget User user);

    default List<RoleDto> roleToRoleDto (List<Role> roles) {
        return roles.stream()
                .map(role -> RoleMapper.INSTANCE.fromRole(role))
                .collect(Collectors.toList());
    }
}
