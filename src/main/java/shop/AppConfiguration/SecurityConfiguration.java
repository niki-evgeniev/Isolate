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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
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
//
//        CookieCsrfTokenRepository cookieCsrfTokenRepository  = CookieCsrfTokenRepository.withHttpOnlyFalse();
//        cookieCsrfTokenRepository.setSecure(true);
//        cookieCsrfTokenRepository.setCookieCustomizer(cookie -> cookie
//                        .sameSite("Lax")        // или "Strict", ако не ползваш cross-site форми
//                        .secure(true));

        httpSecurity.authorizeHttpRequests(
                authorizeRequest -> authorizeRequest
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/css/**", "/fonts/**", "/images/**", "/js/**", "/php/**",
                                "/plugins/**", "/vendor/**").permitAll()
                        .requestMatchers("/robots.txt").permitAll()
                        .requestMatchers("/", "/contact", "/user/sign_in", "/store",
                                "/api/***", "/user/sign_up", "/register",
                                "/users/login-error", "/users/logout").permitAll()
                        .anyRequest().authenticated()
        ).csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        ).formLogin(
                formLogin -> {
                    formLogin
                            .loginPage("/user/sign_in")
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/", true)
                            .failureForwardUrl("/users/login-error");
                }
        ).logout(logout -> logout
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        ).rememberMe(
                rememberMe -> {
                    rememberMe
                            .key(rememberMeKey)
                            .rememberMeParameter("remember-me")
                            .rememberMeCookieName("remember-me");
                }

        ).portMapper(
                httpSecurityHTTPS -> {
                    httpSecurityHTTPS
                            .http(80).mapsTo(443)
                            .http(8080).mapsTo(443);
                }
        )
                .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                        .policyDirectives(
                                "default-src 'self'; " +
                                        "script-src 'self' 'unsafe-inline'; " +
                                        "style-src 'self' 'unsafe-inline' https://fonts.googleapis.com; " +
                                        "font-src 'self' https://fonts.gstatic.com data:; " +
                                        "img-src 'self' data:;"
                        )
                )
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
