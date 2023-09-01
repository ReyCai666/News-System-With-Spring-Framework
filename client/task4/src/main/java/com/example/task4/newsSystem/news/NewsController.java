package com.example.task4.newsSystem.news;

import com.example.task4.newsSystem.comment.CommentService;
import com.example.task4.newsSystem.user.User;
import com.example.task4.newsSystem.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "news")
public class NewsController {
    private final NewsService newsService;
    private final CommentService commentService;
    private final UserService userService;

    public NewsController(NewsService newsService, CommentService commentService, UserService userService) {
        this.newsService = newsService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        List<News> newsList = newsService.findAll();
        model.addAttribute("newsList", newsList);
        return "allNews";
    }

    public List<News> getAllNews() {
        return newsService.getNews();
    }

    @GetMapping("/{id}")
    public String getNewsById(@PathVariable Integer id,
                              Model model,
                              HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        Optional<User> userOptional = userService.findUserByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("loggedInUserId", user.getUid());
            News news = newsService.findNewsById(id);
            model.addAttribute("news", news);
        }
        return "news-details";
    }

    @GetMapping("/type/{newsType}")
    public String getNewsByType(@PathVariable News.NewsType newsType, Model model) {
        List<News> newsList = newsService.findNewsByType(newsType);
        model.addAttribute("newsList", newsList);
        return "allNews";
    }

    @GetMapping("/createNews")
    public String displayCreateNewsPage() {
        return "create-news";
    }

    @PostMapping("/createNews")
    public ModelAndView createNews(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        Optional<User> user = userService.findUserByUsername(username);
        if (user.isPresent()) {
            int userId = user.get().getUid();
        }
        int newsId = Integer.parseInt(request.getParameter("newsId"));
        String author = request.getParameter("author");
        String content = request.getParameter("content");
        String coverImage = request.getParameter("cover_image");
        boolean isPromoted = "1".equals(request.getParameter("is_promoted"));
        String releaseDate = request.getParameter("release_date");
        String title = request.getParameter("title");
        String type = request.getParameter("type");

        News news = new News();
        news.setNewsID(newsId);
        news.setAuthor(author);
        news.setContent(content);
        news.setCoverImage(coverImage);
        news.setPromoted(isPromoted);
        news.setReleaseDate(releaseDate);
        news.setViews(0);
        news.setShares(0);
        news.setSaves(0);
        news.setTitle(title);
        news.setUser(user.get());
        news.setType(News.NewsType.valueOf(type));

        newsService.save(news);

        return new ModelAndView("redirect:/news");
    }

}
