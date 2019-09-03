package user.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import user.dto.RoleDto;
import user.model.Role;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role toRole(RoleDto roleDto);

    List<Role> toRoles(List<RoleDto> roleDtos);

    @InheritInverseConfiguration
    RoleDto fromRole(Role role);

    @InheritInverseConfiguration
    List<RoleDto> fromRoles(List<Role> roles);

    void updateRoleFromDto(RoleDto roleDto, @MappingTarget Role role);

    void updateRolesFromDtos(List<RoleDto> roleDto, @MappingTarget List<Role> role);
}
