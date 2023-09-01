package com.example.task4.newsSystem.news;

import com.example.task4.newsSystem.advertisement.Advertisement;
import com.example.task4.newsSystem.comment.Comment;
import com.example.task4.newsSystem.user.User;
import com.example.task4.newsSystem.userNewsCollection.UserNewsCollection;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class News {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_uid", nullable = false)
    private User user;

    @Id
    private int newsID;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public void setSaves(long saves) {
        this.saves = saves;
    }

    public void setShares(long shares) {
        this.shares = shares;
    }

    public boolean isPromoted() {
        return isPromoted;
    }

    public void setPromoted(boolean promoted) {
        isPromoted = promoted;
    }

    public NewsType getType() {
        return type;
    }

    private String title;
    private String coverImage;
    private String releaseDate;
    private String author;
    private String content;
    private long views;
    private long saves;
    private long shares;
    private boolean isPromoted;
    @Enumerated(EnumType.STRING)
    private NewsType type;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "news")
    private List<Comment> commentsList;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "news")
    private List<Advertisement> advertisementList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "news")
    private List<UserNewsCollection> userNewsCollections;

    public News(int newsID, String title, String coverImage, String releaseDate, String author,
                String content, long views, long saves, long shares, boolean isPromoted, NewsType type,
                List<Comment> commentsList, List<Advertisement> advertisementList, List<UserNewsCollection> userNewsCollections){
        this.newsID = newsID;
        this.title = title;
        this.coverImage = coverImage;
        this.releaseDate = releaseDate;
        this.author = author;
        this.content = content;
        this.views = views;
        this.saves = saves;
        this.shares = shares;
        this.isPromoted = isPromoted;
        this.type = type;
        this.commentsList = commentsList;
        this.advertisementList = advertisementList;
        this.userNewsCollections = userNewsCollections;
    }

    public News(int newsID, NewsType type) {
        this.newsID = newsID;
        this.type = type;
    }

    public News() {

    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setType(NewsType type) {
        this.type = type;
    }

    public List<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    public String getContent() {
        return content;
    }

    public long getViews() {
        return views;
    }

    public long getSaves() {
        return saves;
    }

    public long getShares() {
        return shares;
    }

    public int getNewsID() {
        return newsID;
    }

    public List<Advertisement> getAdvertisementList() {
        return advertisementList;
    }

    public List<UserNewsCollection> getUserNewsCollections() {
        return userNewsCollections;
    }

    public void setUserNewsCollections(List<UserNewsCollection> userNewsCollections) {
        this.userNewsCollections = userNewsCollections;
    }

    public void incrementSaves() {
        this.saves+=1;
    }

    public void cancelSaves() {
        this.saves-=1;
    }

    @Override
    public String toString() {
        if (title == null && coverImage == null) {
            return "News{ " +
                    "newsID=" + newsID +
                    ", type=" + type +
                    " }\n";
        } else {
            return "News{\n" +
                    "newsID=" + newsID + ",\n" +
                    "title='" + title + '\'' + ",\n" +
                    "coverImage='" + coverImage + '\'' + ",\n" +
                    "releaseDate='" + releaseDate + '\'' + ",\n" +
                    "author='" + author + '\'' + ",\n" +
                    "content='" + content + '\'' + ",\n" +
                    "views=" + views + ",\n" +
                    "saves=" + saves + ",\n" +
                    "shares=" + shares + ",\n" +
                    "isPromoted=" + isPromoted + ",\n" +
                    "type=" + type + ",\n" +
                    "commentsList=" + commentsList + "\n" +
                    "AdvertisementList=" +advertisementList +
                    '}';
        }
    }


    enum NewsType {
        BREAKING,
        FEATURE_NEWS,
        SPORTS,
        BUSINESS,
        ENTERTAINMENT
    }
}
