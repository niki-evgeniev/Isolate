package shop.Service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.DTO.EmailRequestDTO;
import shop.DTO.UserDto.RegisterUserDTO;
import shop.Entity.Enums.RoleType;
import shop.Entity.User;
import shop.Entity.UserRole;
import shop.Repository.UserRepository;
import shop.Repository.UserRoleRepository;
import shop.Service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final MailjetService mailjetService;

    @Value("${isolate.base-url}")
    private String baseUrl;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           UserRoleRepository userRoleRepository, MailjetService mailjetService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.mailjetService = mailjetService;
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

    @Override
    public boolean registerNewUser(RegisterUserDTO registerUserDTO) {
        Optional<User> findByEmail = userRepository.findByEmail(registerUserDTO.getEmail());
        boolean isRegisterNewUser = false;
        String verificationToken = UUID.randomUUID().toString();

        if (findByEmail.isEmpty()) {
            User user = new User();
            user.setActivate(false);
            user.setRegisterDate(LocalDateTime.now());
            user.setEmail(registerUserDTO.getEmail());
            String encodePassword = passwordEncoder.encode(registerUserDTO.getPassword());
            user.setPassword(encodePassword);
            List<UserRole> getRoleUser = userRoleRepository.findByRoleType(RoleType.USER);
            user.setRoles(getRoleUser);
            user.setVerificationToken(verificationToken);
            user.setTokenCreated(LocalDateTime.now());
            user.setUuid(UUID.randomUUID());
            userRepository.save(user);

            System.out.println("Successful register user with Email " + registerUserDTO.getEmail());
            isRegisterNewUser = true;
        }

        String verificationUrl = baseUrl + "/user/verify?token=" + verificationToken;
        if (isRegisterNewUser) {
            EmailRequestDTO emailRequestDTO = new EmailRequestDTO();
            emailRequestDTO.setToEmail(registerUserDTO.getEmail());
            emailRequestDTO.setSubject("Регистрация нов потребител");
            emailRequestDTO.setHtmlContent("""
                    <h1>Добре дошъл!</h1>
                    <p>Регистрирахте нов потребител с имейл: <strong>%s</strong></p>
                    <p>Кликнете на този линк, за да активирате акаунта си: <a href="%s">%s</a></p>
                    """
                    .formatted(registerUserDTO.getEmail(), verificationUrl, verificationUrl));

            emailRequestDTO.setTextContent("Регистрирахте се в Isolate.bg. Потвърдете имейла си чрез този линк: "
                    + verificationUrl);
            emailRequestDTO.setToName(registerUserDTO.getEmail());
            boolean isEmailSend = mailjetService.sendEmailRegisterUser(emailRequestDTO);
            System.out.println("Email confirm is " + isEmailSend);
            return true;
        }

        return false;
    }

    @Override
    public boolean verifyUser(String token) {
        if (token != null && !token.isBlank()) {
            Optional<User> userVerification = userRepository.findByVerificationToken(token);
            if (userVerification.isPresent()) {
                User user = userVerification.get();
                user.setVerificationToken(null);
                user.setActivate(true);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    private static User mapAdminUser(String encodePassword, List<UserRole> allRoles) {
        User user = new User();
        user.setEmail("admin@admin");
        user.setPassword(encodePassword);
        user.setFirstName("Admin");
        user.setLastName("Adminov");
        user.setRegisterDate(LocalDateTime.now());
        user.setRoles(allRoles);
        user.setActivate(true);
        user.setUuid(UUID.randomUUID());
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
        normalUser.setActivate(true);
        normalUser.setUuid(UUID.randomUUID());
        return normalUser;
    }
}
