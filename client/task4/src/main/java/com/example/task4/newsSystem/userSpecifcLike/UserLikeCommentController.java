package com.example.task4.newsSystem.userSpecifcLike;

import com.example.task4.newsSystem.comment.Comment;
import com.example.task4.newsSystem.user.User;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserLikeCommentController {
    private final UserLikeCommentService userLikeCommentService;

    public UserLikeCommentController(UserLikeCommentService userLikeCommentService) {
        this.userLikeCommentService = userLikeCommentService;
    }

    public void removeCommentLike(Long id) {
        userLikeCommentService.removeCommentLike(id);
    }

    public UserLikedComment getUserCommentLike(User user, Comment comment) {

        Optional<UserLikedComment> optionalUserLikedComment = userLikeCommentService.getUserCommentLike(user, comment);
        if (optionalUserLikedComment.isPresent()) {
            return optionalUserLikedComment.get();
        } else {
            throw new RuntimeException("The user has not liked this comment before: " + user.getUsername());
        }
    }
}
