package com.example.task4.newsSystem.comment;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment findCommentById(Integer commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            return optionalComment.get();
        } else {
            throw new RuntimeException("News not found with id " + commentId);
        }
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteById(Integer commentId) {
        commentRepository.deleteById(commentId);
    }
}
