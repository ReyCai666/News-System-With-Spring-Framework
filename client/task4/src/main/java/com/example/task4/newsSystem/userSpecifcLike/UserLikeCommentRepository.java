package com.example.task4.newsSystem.userSpecifcLike;

import com.example.task4.newsSystem.comment.Comment;
import com.example.task4.newsSystem.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLikeCommentRepository extends JpaRepository<UserLikedComment, Long> {
    Optional<UserLikedComment> findUserLikedCommentByUserAndComment(User user, Comment comment);
}
