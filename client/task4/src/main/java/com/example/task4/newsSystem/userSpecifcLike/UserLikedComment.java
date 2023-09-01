package com.example.task4.newsSystem.userSpecifcLike;

import com.example.task4.newsSystem.comment.Comment;
import com.example.task4.newsSystem.user.User;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_liked_comment")
public class UserLikedComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    public UserLikedComment(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    public UserLikedComment() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLikedComment that = (UserLikedComment) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, comment);
    }

}
