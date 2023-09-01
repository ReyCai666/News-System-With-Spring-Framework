package com.example.task4.newsSystem.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "login")
@RequiredArgsConstructor
public class LoginController {
//    private final UserService userService;
//    private final NewsController  newsController;
//    private final BCryptPasswordEncoder passwordEncoder;
//    private final JwtService jwtService;

    @GetMapping
    public String index() {
        return "login";
    }

//    @PostMapping
//    public String login(@RequestParam("email") String email,
//                        @RequestParam("password") String password,
//                        Model model,
//                        HttpSession session) {
//        Optional<User> user = userService.findUserByEmail(email);
//
//        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(email, password);
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            session.setAttribute("username", user.get().getUsername());
//            session.setAttribute("role", user.get().getRole().name());
//            List<News> allNews = newsController.getAllNews();
//            model.addAttribute("newsList", allNews);
//            return "allNews";
//        } else {
//            model.addAttribute("loginError", "Invalid username or password.");
//            return "login";
//        }
//    }
}
