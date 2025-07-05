package shop.Service.Impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.Entity.User;
import shop.Entity.UserRole;
import shop.Repository.UserRepository;
import shop.Repository.UserRoleRepository;
import shop.Service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void addAdminProfile() {
        if (userRepository.count() == 0) {
            String password = "1234";
            String encodePassword = passwordEncoder.encode(password);
            List<UserRole> allRoles = userRoleRepository.findAll();

            User user = mapAdminUser(encodePassword, allRoles);
            User normalUser = mapNormalUser(encodePassword, allRoles);

            userRepository.save(user);
            userRepository.save(normalUser);
        }
    }

    @Override
    public boolean checkEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    private static User mapAdminUser(String encodePassword, List<UserRole> allRoles) {
        User user = new User();
        user.setEmail("admin@admin");
        user.setPassword(encodePassword);
        user.setFirstName("Admin");
        user.setLastName("Adminov");
        user.setRegisterDate(LocalDateTime.now());
        user.setRoles(allRoles);
        return user;
    }

    private static User mapNormalUser(String encodePassword, List<UserRole> allRoles) {
        User normalUser = new User();
        normalUser.setEmail("user@user");
        normalUser.setPassword(encodePassword);
        normalUser.setFirstName("User");
        normalUser.setLastName("Userov");
        normalUser.setRegisterDate(LocalDateTime.now());
        normalUser.setRoles(List.of(allRoles.getFirst()));
        return normalUser;
    }
}
