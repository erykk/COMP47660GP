package ie.ucd.COMP47660GP.repositories;

import javax.persistence.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ie.ucd.COMP47660GP.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    List<User> findAll();
}
