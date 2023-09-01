package com.example.task4.newsSystem.dashboard;

import com.example.task4.newsSystem.user.User;
import com.example.task4.newsSystem.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping(path = "dashboard")
public class DashboardController {
    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model,
                        HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        Optional<User> user = userService.findUserByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        } else {
            model.addAttribute("error", "user not found");
        }
        return "user-dashboard";
    }

    @PutMapping(path = "/update-username")
    public String updateUsername(@RequestParam("username") String username,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttrs) {
        if (username==null || username.isEmpty()) {
            redirectAttrs.addFlashAttribute("message", "Username can't be null");
            redirectAttrs.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/dashboard";
        }
        HttpSession session = request.getSession(false);
        Optional<User> user = userService.findUserByUsername((String) session.getAttribute("username"));
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setUsername(username);
            userService.save(user1);
            session.setAttribute("username", username);
            redirectAttrs.addFlashAttribute("message", "Username updated successfully");
            redirectAttrs.addFlashAttribute("alertClass", "alert-success");
        } else {
            redirectAttrs.addFlashAttribute("message", "User not found");
            redirectAttrs.addFlashAttribute("alertClass", "alert-danger");
        }

        return "redirect:/dashboard";
    }
}
