package com.example.task4.newsSystem.userSpecifcLike;

import com.example.task4.newsSystem.comment.Comment;
import com.example.task4.newsSystem.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLikeCommentService {
    private final UserLikeCommentRepository userLikeCommentRepository;

    public UserLikeCommentService(UserLikeCommentRepository userLikeCommentRepository) {
        this.userLikeCommentRepository = userLikeCommentRepository;
    }

    public void removeCommentLike(Long id) {
        boolean exists = userLikeCommentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("ID: " + id + " does not exists!");
        }
        userLikeCommentRepository.deleteById(id);
    }

    public Optional<UserLikedComment> getUserCommentLike(User user, Comment comment) {
        return userLikeCommentRepository.findUserLikedCommentByUserAndComment(user, comment);
    }
}
