package com.example.task4.newsSystem.userNewsCollection;

import com.example.task4.newsSystem.news.News;
import com.example.task4.newsSystem.news.NewsService;
import com.example.task4.newsSystem.user.User;
import com.example.task4.newsSystem.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserNewsCollectionController {


    private final UserService userService;
    private final NewsService newsService;
    private final UserNewsCollectionService userNewsCollectionService;

    public UserNewsCollectionController(UserService userService, NewsService newsService, UserNewsCollectionService userNewsCollectionService) {
        this.userService = userService;
        this.newsService = newsService;
        this.userNewsCollectionService = userNewsCollectionService;
    }

    @PostMapping(path = "/news/save-to-collection")
    @Transactional
    public String saveNews(@RequestParam("newsId") int newsId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        Optional<User> userOptional = userService.findUserByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            News news = newsService.findNewsById(newsId);
            boolean saved = user.getNewsCollections().stream().anyMatch(newsSaved -> newsSaved.getNews().equals(news));
            if (!saved) {
                UserNewsCollection userNewsCollection = new UserNewsCollection(user, news);
                userNewsCollection.setNews(news);
                user.getNewsCollections().add(userNewsCollection);
                news.incrementSaves();
            } else {
                UserNewsCollection userNewsCollection = userNewsCollectionService.getNewsCollection(user, news);
                user.getNewsCollections().remove(userNewsCollection);
                userNewsCollectionService.deleteFromNewsCollection(user, news);
                news.cancelSaves();
            }
            newsService.save(news);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
