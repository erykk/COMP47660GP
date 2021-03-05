package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByName(String name);
}
