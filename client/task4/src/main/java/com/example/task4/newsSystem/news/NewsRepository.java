package com.example.task4.newsSystem.news;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findNewsByType(News.NewsType newsType);
}
