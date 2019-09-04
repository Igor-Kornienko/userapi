package user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import user.dto.UserDto;
import user.model.User;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto fromUser(User user);

    void updateUserFromDto(UserDto userDto, @MappingTarget User user);
}
