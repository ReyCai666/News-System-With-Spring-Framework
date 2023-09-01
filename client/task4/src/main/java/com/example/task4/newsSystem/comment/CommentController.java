package com.example.task4.newsSystem.comment;

import com.example.task4.newsSystem.news.News;
import com.example.task4.newsSystem.news.NewsService;
import com.example.task4.newsSystem.user.User;
import com.example.task4.newsSystem.user.UserService;
import com.example.task4.newsSystem.userSpecifcLike.UserLikeCommentController;
import com.example.task4.newsSystem.userSpecifcLike.UserLikedComment;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "comment")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final NewsService newsService;
    private int commentIdCounter = 2;

    private final UserLikeCommentController userLikeCommentController;
    private final Long DEFAULT_LIKES = 0L;
    private final Long DEFAULT_DISLIKES= 0L;

    public CommentController(CommentService commentService, UserService userService, NewsService newsService, UserLikeCommentController userLikeCommentController) {
        this.commentService = commentService;
        this.userService = userService;
        this.newsService = newsService;
        this.userLikeCommentController = userLikeCommentController;
    }

    @GetMapping
    public List<Comment> getComments() {
        return commentService.getComments();
    }

    @PostMapping(path = "/like")
    @Transactional
    public String likeComment(@RequestParam("commentId") Integer commentId,
                              HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        Optional<User> userOptional = userService.findUserByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Comment comment = commentService.findCommentById(commentId);
            if(comment!=null) {
                boolean hasLiked = comment.getUserLikes().stream()
                        .anyMatch(like -> like.getUser().equals(user));
                if (!hasLiked) {
                    UserLikedComment userLikedComment = new UserLikedComment(user, comment);
                    userLikedComment.setComment(comment); // Set the comment
                    comment.getUserLikes().add(userLikedComment); // Add to the comment's likedByUsers collection
                    comment.incrementLike();
                } else {
                    UserLikedComment userLikedComment = userLikeCommentController.getUserCommentLike(user, comment);
                    comment.getUserLikes().remove(userLikedComment);
                    comment.cancelLike();
                    userLikeCommentController.removeCommentLike(userLikedComment.getId());
                }
                commentService.save(comment);
            }
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping(path = "/create")
    @Transactional
    public String makeComments(@RequestParam("newsId") Integer newsId,
                             @RequestParam("comment") String commentContent,
                             HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        Optional<User> userOptional = userService.findUserByUsername(username);
        News news = newsService.findNewsById(newsId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Comment comment = new Comment();
            Timestamp currentTimestamp = Timestamp.from(Instant.now());
            comment.setNews(news);
            comment.setCommentTime(currentTimestamp);
            comment.setCommentContent(commentContent);
            comment.setUserID(user.getUid());
            comment.setDislikes(DEFAULT_DISLIKES);
            comment.setLikes(DEFAULT_LIKES);
            comment.setQuotedCommentID(0);
            comment.setLikedByUsers(Collections.emptySet());

            commentService.save(comment);
            System.out.println(comment);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @DeleteMapping(path = "/delete")
    public String deleteComment(@RequestParam("commentId") Integer commentId,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttrs) {
        Comment comment = commentService.findCommentById(commentId);
        if (comment == null) {
            redirectAttrs.addFlashAttribute("message", "Comment cannot be null.");
            redirectAttrs.addFlashAttribute("alertClass", "alert-danger");
        }

        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        Optional<User> userOptional = userService.findUserByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (comment.getUserID() != user.getUid()) {
                redirectAttrs.addFlashAttribute("message", "Access denied! You are not the author of this comment!");
                redirectAttrs.addFlashAttribute("alertClass", "alert-danger");
            }
        }
        commentService.deleteById(commentId);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
