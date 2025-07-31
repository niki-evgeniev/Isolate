package shop.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import shop.Entity.User;
import shop.Entity.UserRole;
import shop.Entity.Enums.RoleType;
import shop.Repository.UserRepository;
import shop.Service.Impl.ShopUserService;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShopUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ShopUserService shopUserService;

    private User testUser;

    @BeforeEach
    void setUp() {
        UserRole role = new UserRole();
        role.setRoleType(RoleType.ADMIN);

        testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setPassword("password123");
        testUser.setRoles(List.of(role));
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        when(userRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(testUser));

        UserDetails userDetails = shopUserService.loadUserByUsername("test@example.com");

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo("test@example.com");
        assertThat(userDetails.getAuthorities()).extracting("authority")
                .contains("ROLE_ADMIN");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByEmail("unknown@example.com"))
                .thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () ->
                shopUserService.loadUserByUsername("unknown@example.com"));
    }
}
