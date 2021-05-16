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
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        List<Role> roles = roleRepository.findAll();
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveExecUser(User user){
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null && !existingUser.getExec()) {
            existingUser.setExec(true);
            save(existingUser);
        } else {
            user.setRoles(Arrays.asList(roleRepository.findByName("EXEC")));
            user.setExec(true);
            save(user);
        }
    }

    public void saveGuestUser(User user){
        user.setRoles(Arrays.asList(roleRepository.findByName("GUEST")));
        user.setExec(false);
        save(user);
    }

    public boolean deleteExecUser(User user, String password){
        if (password.equals(user.getPassword())) {
            System.out.println("service:" + user.getEmail());
            System.out.println("service" + bCryptPasswordEncoder.encode(user.getPassword()));
            user.setExec(false);
            userRepository.save(user);
            return true;
        }
        return false;
    }






    // Assignment 3
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
