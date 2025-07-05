package shop.AppConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import shop.Repository.UserRepository;
import shop.Service.Impl.ShopUserService;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    private final String rememberMeKey;

    public SecurityConfiguration(@Value("${isolate.remember.me.key}")
                                 String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(
                authorizeRequest -> authorizeRequest
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/css/**", "/fonts/**", "/images/**", "/js/**", "/php/**",
                                "/plugins/**", "/vendor/**").permitAll()
                        .requestMatchers("/robots.txt").permitAll()
                        .requestMatchers("/", "/contact","/sign_in", "/store",
                                "/api/***", "/user/signUp", "/register").permitAll()
                        .anyRequest().authenticated()

//        ).csrf(csrf -> csrf
//                .requireCsrfProtectionMatcher(request -> {
//                    String path = request.getServletPath();
//                    return !path.startsWith("/api/");
//                })
        ).formLogin(
                formLogin -> {
                    formLogin
                            .loginPage("/sign_in")
//                            .loginProcessingUrl("/sign_in")
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/", true)
                            .failureForwardUrl("/login-error");
//                            .failureUrl("/sign_in?error=true");
                }
        ).logout(
                logout -> {
                    logout.logoutUrl("/users/logout")
                            .logoutSuccessUrl("/")
                            .invalidateHttpSession(true);

                }
        ).rememberMe(
                rememberMe -> {
                    rememberMe
                            .key(rememberMeKey)
                            .rememberMeParameter("remember-me")
                            .rememberMeCookieName("remember-me");
                }
        );
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ShopUserService(userRepository);
    }
}
