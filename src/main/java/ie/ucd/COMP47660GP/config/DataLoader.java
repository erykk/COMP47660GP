package ie.ucd.COMP47660GP.config;

import ie.ucd.COMP47660GP.entities.Role;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.repositories.RoleRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    boolean alreadySetup = false;

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        if (alreadySetup) return;

        createRoleIfNotExists("GUEST");
        createRoleIfNotExists("EXEC");
        Role role = roleRepository.findByName("EXEC");
        if (userRepository.findByEmail("test@test.com") == null) {
            User user = new User();
            user.setFirstName("Test");
            user.setLastName("McTest");
            user.setEmail("test@test.com");
            user.setPassword("test");
            user.setExec(true);
            user.setRoles(Arrays.asList(role));
            userRepository.save(user);
        }

        alreadySetup = true;
    }

    @Transactional
    public Role createRoleIfNotExists(String name){
        Role role = roleRepository.findByName(name);
        if (role == null){
            role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
        return role;
    }
}
