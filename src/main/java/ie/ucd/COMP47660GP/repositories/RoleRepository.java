package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.Role;
import ie.ucd.COMP47660GP.exception.NoSuchRoleException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

    @Query("select r from Role r where r.name = :name")
    Role findByName(String name) throws NoSuchRoleException;
}
