package ie.ucd.COMP47660GP.service.impl;

import ie.ucd.COMP47660GP.entities.Role;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.repositories.RoleRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private void save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<Role>(roleRepository.findAll()));
        userRepository.save(user);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void saveExecUser(User user){
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null && !existingUser.getExec()) {
            existingUser.setExec(true);
            save(existingUser);
        } else {
            user.setExec(true);
            save(user);
        }
        save(user);
    }

    public void saveGuestUser(User user){
        user.setExec(false);
        save(user);
    }

    public boolean deleteExecUser(User user, String password){
        if (user.getPassword().equals(bCryptPasswordEncoder.encode(password))) {
            System.out.println("service:" + user.getEmail());
            System.out.println("service" + bCryptPasswordEncoder.encode( user.getPassword()));
            user.setExec(false);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    /*
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){ return new BCryptPasswordEncoder(); }

     */
}