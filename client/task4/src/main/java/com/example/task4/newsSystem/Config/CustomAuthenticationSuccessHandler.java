package com.example.task4.newsSystem.Config;

import com.example.task4.newsSystem.user.User;
import com.example.task4.newsSystem.user.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findUserByUsername(userDetails.getUsername());
        if (user.isPresent()) {
            System.out.println("Role after authentication: " + user.get().getRole().name());
            session.setAttribute("username", user.get().getUsername());
            session.setAttribute("role", user.get().getRole().name());
        }
        System.out.println(user.isPresent());
        System.out.println(authentication.getPrincipal());
        response.sendRedirect("/news");
    }
}
