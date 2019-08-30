package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}