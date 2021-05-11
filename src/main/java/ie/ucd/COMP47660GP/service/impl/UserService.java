package ie.ucd.COMP47660GP.service.impl;

import ie.ucd.COMP47660GP.entities.Role;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.repositories.RoleRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

public interface UserService {

    void save(User user);
    User findByEmail(String email);
    void saveGuestUser(User user);
    void saveExecUser(User user);
    boolean deleteExecUser(User user, String password);

    // Assignment 3
    User findByUsername(String username);





//    User findByUsername(String username);


//    private void save(User user){
//        user.setPassword(user.getPassword());
//        user.setRoles(new HashSet<Role>(roleRepository.findAll()));
//        userRepository.save(user);
//    }
//
//    public User findByEmail(String email){
//        return userRepository.findByEmail(email);
//    }
//
//    public void saveExecUser(User user){
//        User existingUser = userRepository.findByEmail(user.getEmail());
//        if (existingUser != null && !existingUser.getExec()) {
//            existingUser.setExec(true);
//            save(existingUser);
//        } else {
//            user.setRoles(Arrays.asList(roleRepository.findByName("EXEC")));
//            user.setExec(true);
//            save(user);
//        }
//    }
//
//    public void saveGuestUser(User user){
//        user.setRoles(Arrays.asList(roleRepository.findByName("GUEST")));
//        user.setExec(false);
//        save(user);
//    }
//
//    public boolean deleteExecUser(User user, String password){
//        if (password.equals(user.getPassword())) {
//            System.out.println("service:" + user.getEmail());
//            System.out.println("service" + bCryptPasswordEncoder.encode(user.getPassword()));
//            user.setExec(false);
//            userRepository.save(user);
//            return true;
//        }
//        return false;
//    }

}
