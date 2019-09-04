package user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import user.dto.RoleDto;
import user.model.Role;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDto fromRole(Role role);

    void updateRoleFromDto(RoleDto roleDto, @MappingTarget Role role);
}
