package co.istad.reporting.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN", "USER")
                .build();

        UserDetails manager = User.builder()
                .username("manager")
                .password("{noop}manager")
                .roles("MANAGER", "USER")
                .build();

        UserDetails staff = User.builder()
                .username("staff")
                .password("{noop}staff")
                .roles("STAFF", "USER")
                .build();

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(admin);
        userDetailsManager.createUser(manager);
        userDetailsManager.createUser(staff);

        return userDetailsManager;
    }

    @Bean
    SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {

        // TODO: What security you want to customize?
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/anonymous").permitAll()
                        .requestMatchers("/api/v1/admins").hasRole("ADMIN")
                        .requestMatchers("/api/v1/managers").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/api/v1/staffs").hasAnyRole("STAFF", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

}
