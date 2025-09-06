package uz.pdp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SpringSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(
                        auth -> {
                            auth
//                                    .requestMatchers("/auth/**", "/admin/**", "/secure").hasRole("ADMIN")
//                                    .requestMatchers("/statistics/**").hasAnyRole("ADMIN", "MANAGER")
                                    .requestMatchers("/login", "/register").permitAll()
                                    .anyRequest()
                                    .authenticated();
                        })
                .userDetailsService(userDetailsService);

        http.formLogin(
                formLoginConfigurer -> {
                    formLoginConfigurer
                            .loginPage("/login")
                            .defaultSuccessUrl("/index", true)
                            .failureUrl("/login?error=true")
                            .usernameParameter("username")
                            .passwordParameter("password");

                }
        );


        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
