package com.example.task4.newsSystem.news;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    private final NewsRepository newsRepository;


    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getNews() {
        return newsRepository.findAll();
    }

    public News findNewsById(Integer id) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()) {
            return optionalNews.get();
        } else {
            throw new RuntimeException("News not found with id " + id);
        }
    }

    public void save(News news) {
        newsRepository.save(news);
    }

    public List<News> findNewsByType(News.NewsType newsType) {
        return newsRepository.findNewsByType(newsType);
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }
}
