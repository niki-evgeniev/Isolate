package shop.Service.Impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import shop.Entity.User;
import shop.Entity.UserRole;
import shop.Repository.UserRepository;

public class ShopUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public ShopUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByEmail(email)
                .map(ShopUserService::mapUser)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found"));

        System.out.printf("LOGIN USER " + userDetails.getUsername() + "%n");
        return userDetails;
    }

    private static UserDetails mapUser(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRoles()
                        .stream()
                        .map(ShopUserService::map)
                        .toList())
                .build();
    }

    private static GrantedAuthority map(UserRole userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole.getRoleType().name());
    }
}
