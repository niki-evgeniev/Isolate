package shop.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.DTO.EmailRequestDTO;
import shop.DTO.UserDto.RegisterUserDTO;
import shop.Entity.Enums.RoleType;
import shop.Entity.User;
import shop.Entity.UserRole;
import shop.Repository.UserRepository;
import shop.Repository.UserRoleRepository;
import shop.Service.Impl.MailjetService;
import shop.Service.Impl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private MailjetService mailjetService;

    @InjectMocks
    private UserServiceImpl userService;

    private RegisterUserDTO registerUserDTO;
    private User testUser;

    @BeforeEach
    void setUp() {
        registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setEmail("user@example.com");
        registerUserDTO.setPassword("password");

        testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("user@example.com");
        testUser.setPassword("encodedPass");
        testUser.setRegisterDate(LocalDateTime.now());
        testUser.setUuid(UUID.randomUUID());
    }

    @Test
    void testCheckEmailExist_True() {
        when(userRepository.existsByEmail("user@example.com")).thenReturn(true);

        boolean exists = userService.checkEmailExist("user@example.com");

        assertThat(exists).isTrue();
        verify(userRepository).existsByEmail("user@example.com");
    }

    @Test
    void testCheckEmailExist_False() {
        when(userRepository.existsByEmail("unknown@example.com")).thenReturn(false);

        boolean exists = userService.checkEmailExist("unknown@example.com");

        assertThat(exists).isFalse();
    }

    @Test
    void testRegisterNewUser_Success() {
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPass");

        UserRole roleUser = new UserRole();
        roleUser.setRoleType(RoleType.USER);
        when(userRoleRepository.findByRoleType(RoleType.USER)).thenReturn(List.of(roleUser));
        when(mailjetService.sendEmailRegisterUser(any(EmailRequestDTO.class))).thenReturn(true);

        boolean result = userService.registerNewUser(registerUserDTO);

        assertThat(result).isTrue();
        verify(userRepository).save(any(User.class));
        verify(mailjetService).sendEmailRegisterUser(any(EmailRequestDTO.class));
    }

    @Test
    void testRegisterNewUser_EmailAlreadyExists() {
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(testUser));

        boolean result = userService.registerNewUser(registerUserDTO);

        assertThat(result).isFalse();
        verify(userRepository, never()).save(any());
    }

    @Test
    void testVerifyUser_Success() {
        testUser.setVerificationToken("token123");
        when(userRepository.findByVerificationToken("token123")).thenReturn(Optional.of(testUser));

        boolean verified = userService.verifyUser("token123");

        assertThat(verified).isTrue();
        verify(userRepository).save(testUser);
    }

    @Test
    void testVerifyUser_InvalidToken() {
        when(userRepository.findByVerificationToken("invalid")).thenReturn(Optional.empty());

        boolean verified = userService.verifyUser("invalid");

        assertThat(verified).isFalse();
    }
}
