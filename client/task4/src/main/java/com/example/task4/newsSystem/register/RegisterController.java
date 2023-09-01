package com.example.task4.newsSystem.register;

import com.example.task4.newsSystem.user.User;
import com.example.task4.newsSystem.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.time.Instant;

@Controller
@RequestMapping(path = "register")
public class RegisterController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String index() {
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute User user) {
        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        user.setLastLogin(currentTimestamp);
        user.setProfilePicture("image.jpg");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        System.out.println(user);
        return "login";
    }
}
