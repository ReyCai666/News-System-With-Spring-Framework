package com.example.task4.newsSystem.advertisement;

import com.example.task4.newsSystem.news.News;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Advertisement {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "newsid", nullable = false)
    private News news;
    @Id
    private int id;
    private String title;
    private String image;
    private String content;
    private String placementIdentifier;
    private Date expiryDate;
    @ElementCollection
    private List<String> regionalRestrictions;
    @ElementCollection
    private List<String> deviceRestrictions;
    private long clickVolume;
    private long exposure;
    @Enumerated(EnumType.STRING)
    private PlacementWeight placementWeight;
    @Enumerated(EnumType.STRING)
    private PlacementType placementType;

    public Advertisement(Integer id, String title, String image, String content, String placementIdentifier,
                         PlacementWeight placementWeight, PlacementType placementType,
                         List<String> regionalRestrictions, List<String> deviceRestrictions, Date expiryDate) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.content = content;
        this.placementWeight = placementWeight;
        this.placementIdentifier = placementIdentifier;
        this.placementType = placementType;
        this.expiryDate = expiryDate;
        this.regionalRestrictions = regionalRestrictions;
        this.deviceRestrictions = deviceRestrictions;
    }

    public Advertisement() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public String getPlacementIdentifier() {
        return placementIdentifier;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", content='" + content + '\'' +
                ", placementIdentifier='" + placementIdentifier + '\'' +
                '}';
    }

    enum PlacementWeight {
        LOW,
        MEDIUM,
        HIGH
    }
    enum PlacementType {
        SPLASH,
        POPUP,
        INLINE_HOME,
        INLINE_COMMENTS,
        INLINE_NEWS,
        INLINE_END
    }
}
