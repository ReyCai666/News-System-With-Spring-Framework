package com.example.task4.newsSystem.userNewsCollection;

import com.example.task4.newsSystem.news.News;
import com.example.task4.newsSystem.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserNewsCollectionService {
    private final UserNewsCollectionRepository newsCollectionRepository;

    public UserNewsCollectionService(UserNewsCollectionRepository newsCollectionRepository) {
        this.newsCollectionRepository = newsCollectionRepository;
    }

    public void deleteFromNewsCollection(User user, News news) {
        newsCollectionRepository.deleteByNewsAndAndUser(news, user);
    }

    public UserNewsCollection getNewsCollection(User user, News news) {
        Optional<UserNewsCollection> optionalUserNewsCollection = newsCollectionRepository.findByUserAndNews(user, news);
        if (optionalUserNewsCollection.isPresent()) {
            return optionalUserNewsCollection.get();
        } else {
            throw new RuntimeException("The user has not liked this comment before: " + user.getUsername());
        }
    }
}
