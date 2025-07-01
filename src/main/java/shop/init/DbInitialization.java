package shop.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shop.Service.UserRoleService;
import shop.Service.UserService;

@Component
public class DbInitialization implements CommandLineRunner {

    private final UserRoleService userRoleService;
    private final UserService userService;

    public DbInitialization(UserRoleService userRoleService, UserService userService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userRoleService.addRole();
        userService.addAdminProfile();
    }
}
