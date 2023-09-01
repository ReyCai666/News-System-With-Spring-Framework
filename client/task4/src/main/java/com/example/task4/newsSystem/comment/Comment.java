package com.example.task4.newsSystem.comment;

import com.example.task4.newsSystem.news.News;
import com.example.task4.newsSystem.userSpecifcLike.UserLikedComment;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    private int userID;
    private String commentContent;
    private Timestamp commentTime;
    private long likes;
    private long dislikes;
    private int quotedCommentID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "newsid", nullable = false)
    private News news;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private Set<UserLikedComment> likedByUsers = new HashSet<>();

    public Comment(int commentID, int userID, String commentContent, Timestamp commentTime,
                    long likes, long dislikes, int quotedCommentID) {
        this.commentID = commentID;
        this.userID = userID;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.likes = likes;
        this.dislikes = dislikes;
        this.quotedCommentID = quotedCommentID;
    }

    public Comment() {
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getCommentID() {
        return commentID;
    }

    public int getUserID() {
        return userID;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public long getLikes() {
        return likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    public int getQuotedCommentID() {
        return quotedCommentID;
    }

    public Set<UserLikedComment> getUserLikes() {
        return likedByUsers;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public void setDislikes(long dislikes) {
        this.dislikes = dislikes;
    }

    public void setQuotedCommentID(int quotedCommentID) {
        this.quotedCommentID = quotedCommentID;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public void setLikedByUsers(Set<UserLikedComment> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    @Override
    public String toString() {
        return "Comments{\n" +
                "commentID=" + commentID + ",\n" +
                "userID=" + userID + ",\n" +
                "commentContent='" + commentContent + '\'' + ",\n" +
                "commentTime=" + commentTime + ",\n" +
                "likes=" + likes + ",\n" +
                "dislikes=" + dislikes + ",\n" +
                "quotedCommentID=" + quotedCommentID + "\n" +
                '}';
    }

    public void incrementLike() {
        this.likes+=1;
    }

    public void cancelLike() {
        this.likes-=1;
    }
}
