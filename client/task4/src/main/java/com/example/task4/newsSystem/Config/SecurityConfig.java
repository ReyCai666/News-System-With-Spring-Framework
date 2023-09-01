package com.example.task4.newsSystem.Config;

import com.example.task4.newsSystem.user.CustomUserDetailsService;
import com.example.task4.newsSystem.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/login/**", "/register/**", "/oauth2/authorization/github", "/actuator/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/news", true)
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(new CustomAuthenticationSuccessHandler(userRepository))
                        .permitAll()
                )
                .oauth2Login(oAuthLogin -> oAuthLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/news", true)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .userDetailsService(customUserDetailsService);
        return http.build();
    }
}

