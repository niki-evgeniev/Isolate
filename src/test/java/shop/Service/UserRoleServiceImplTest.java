package shop.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.Entity.Enums.RoleType;
import shop.Entity.UserRole;
import shop.Repository.UserRoleRepository;
import shop.Service.Impl.UserRoleServiceImpl;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRoleServiceImplTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleServiceImpl userRoleService;

    @Test
    void testAddRole_WhenNoRolesExist() {
        when(userRoleRepository.count()).thenReturn(0L);

        userRoleService.addRole();

        // Проверка, че save е извикан за всеки RoleType
        verify(userRoleRepository, times(RoleType.values().length))
                .save(any(UserRole.class));
    }

    @Test
    void testAddRole_WhenRolesAlreadyExist() {
        when(userRoleRepository.count()).thenReturn(3L);

        userRoleService.addRole();

        // Проверка, че не се записва нова роля
        verify(userRoleRepository, never()).save(any(UserRole.class));
    }
}
