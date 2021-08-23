package am.shoppingapp.service;

import am.shoppingapp.entity.Role;
import am.shoppingapp.entity.RoleName;
import am.shoppingapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import am.shoppingapp.repository.RoleRepository;
import am.shoppingapp.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public User findByLogin(String login) {
        return userRepository.findByName(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPass())) {
                return user;
            }
        } else {
            throw new IllegalArgumentException("User not registered!!!");
        }
        return null;
    }

    public User saveUser(User user) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleName.ROLE_USER);
        Role userRole = optionalRole.orElseGet(() -> roleRepository.save(Role.builder().name(RoleName.ROLE_USER).build()));

        user.setRole(userRole);
        user.setPass(passwordEncoder.encode(user.getPass()));
        return userRepository.save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
