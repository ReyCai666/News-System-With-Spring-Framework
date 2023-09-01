package com.example.task4.newsSystem.userNewsCollection;

import com.example.task4.newsSystem.news.News;
import com.example.task4.newsSystem.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserNewsCollectionRepository extends JpaRepository<UserNewsCollection, Long> {
    void deleteByNewsAndAndUser(News news, User user);

    Optional<UserNewsCollection> findByUserAndNews(User user, News news);
}
