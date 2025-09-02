package uz.pdp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                        auth -> {
                            auth
                                    .requestMatchers("/login")
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated();
                        });

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
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .roles("ADMIN")
                .password("123")
                .build();


        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .roles("USER")
                .password("123")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

}
