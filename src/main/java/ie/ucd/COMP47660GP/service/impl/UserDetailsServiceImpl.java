package ie.ucd.COMP47660GP.service.impl;

import ie.ucd.COMP47660GP.entities.Role;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchUserException;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // treat Username as Email
    // TODO update query to use email, !username

    public UserDetails loadUserByUsername(String email){
        User user = userRepository.findByEmail(email);
        if (user == null) throw new NoSuchUserException();

        Set<GrantedAuthority> authoritySet = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles()){
            authoritySet.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authoritySet);
    }

}
