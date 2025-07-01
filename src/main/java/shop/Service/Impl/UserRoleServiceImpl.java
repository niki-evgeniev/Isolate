package shop.Service.Impl;

import org.springframework.stereotype.Service;
import shop.Entity.Enums.RoleType;
import shop.Entity.UserRole;
import shop.Repository.UserRoleRepository;
import shop.Service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void addRole() {
        if (userRoleRepository.count() == 0) {
            RoleType[] roleValues = RoleType.values();
            for (RoleType roleValue : roleValues) {
                UserRole userRole = new UserRole();
                userRole.setRoleType(roleValue);
                userRoleRepository.save(userRole);
            }
        }
    }
}
