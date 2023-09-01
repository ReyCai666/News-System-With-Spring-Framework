package com.example.task4.newsSystem.userNewsCollection;

import com.example.task4.newsSystem.news.News;
import com.example.task4.newsSystem.user.User;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class UserNewsCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    public UserNewsCollection(User user, News news) {
        this.user = user;
        this.news = news;
    }

    public UserNewsCollection() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNewsCollection that = (UserNewsCollection) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(news, that.news);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, news);
    }
}
