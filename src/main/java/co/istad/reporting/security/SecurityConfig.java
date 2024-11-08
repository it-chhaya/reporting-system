package co.istad.reporting.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

//    @Bean
//    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}admin")
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails manager = User.builder()
//                .username("manager")
//                .password("{noop}manager")
//                .roles("MANAGER", "USER")
//                .build();
//
//        UserDetails staff = User.builder()
//                .username("staff")
//                .password("{noop}staff")
//                .roles("STAFF", "USER")
//                .build();
//
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(admin);
//        userDetailsManager.createUser(manager);
//        userDetailsManager.createUser(staff);
//
//        return userDetailsManager;
//    }


    @Bean
    DaoAuthenticationProvider configDaoAuth() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }


    @Bean
    SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {

        // TODO: What security you want to customize?
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/anonymous").permitAll()
                        .requestMatchers("/api/v1/admins").hasAnyAuthority("report:write", "report:read", "user:read", "user:write")
                        .requestMatchers("/api/v1/managers").hasAnyAuthority("report:write", "report:read", "user:read")
                        .requestMatchers("/api/v1/staffs").hasAnyAuthority("report:write", "report:read")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
    }

}
