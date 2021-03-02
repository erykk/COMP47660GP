package ie.ucd.COMP47660GP.repositories;

import org.springframework.data.jpa.repository.Query;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchUserException;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    List<User> findAll();

    @Query("select u from User u where u.id = :id")
    User findUser(int id) throws NoSuchUserException;

    @Query("select u from User u where u.email = :email")
    User findEmail(String email) throws NoSuchUserException;

    @Transactional
    @Modifying
    @Query("update User u set u.id = :updated_id where u.id = :current_id")
    void updateUserId(int current_id, int updated_id) throws NoSuchUserException;

    User findByEmail(String email);

    //@Query("SELECT u FROM User u WHERE u.email = ?1")
    //User findByEmail(String email);
}
