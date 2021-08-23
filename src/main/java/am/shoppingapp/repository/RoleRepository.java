package am.shoppingapp.repository;

import am.shoppingapp.entity.Role;
import am.shoppingapp.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName role_user);
}
