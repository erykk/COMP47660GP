package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchUserException;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    List<User> findAll();

    User findByEmail(String email);

//    User findUser(String id) throws NoSuchUserException;
//
//    void createMember(String name, String surname, String address, String phone, String email);
}
