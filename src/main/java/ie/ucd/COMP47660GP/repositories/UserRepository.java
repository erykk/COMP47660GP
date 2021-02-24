package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchFlightException;
import ie.ucd.COMP47660GP.exception.NoSuchUserException;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    List<User> findAll();

//    User findUser(String id) throws NoSuchUserException;
//
//
}
