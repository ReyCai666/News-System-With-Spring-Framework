package com.example.task4.newsSystem.user;

import com.example.task4.newsSystem.news.News;
import com.example.task4.newsSystem.userNewsCollection.UserNewsCollection;
import com.example.task4.newsSystem.userSpecifcLike.UserLikedComment;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "USER")
public class User implements UserDetails {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @Column(name = "phone_number")
    private String phoneNumber;

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "username")
    private String username;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserNewsCollection> newsCollections = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<News> browsingHistory;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserLikedComment> likedComments = new HashSet<>();

    public User(int uid, String phoneNumber, String profilePicture, String username, Timestamp lastLogin,
                 String email, String password) {
        this.uid = uid;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.username = username;
        this.lastLogin = lastLogin;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public Set<UserNewsCollection> getNewsCollections() {
        return newsCollections;
    }

    public void setNewsCollections(Set<UserNewsCollection> newsCollections) {
        this.newsCollections = newsCollections;
    }

    public List<News> getBrowsingHistory() {
        return browsingHistory;
    }

    public void setBrowsingHistory(List<News> browsingHistory) {
        this.browsingHistory = browsingHistory;
    }

    public int getUid() {
        return uid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String toString() {
        return "Users{\n" +
                "uid=" + uid + ",\n" +
                "phoneNumber='" + phoneNumber + '\'' + ",\n" +
                "profilePicture='" + profilePicture + '\'' + ",\n" +
                "username='" + username + '\'' + ",\n" +
                "lastLogin=" + lastLogin + ",\n" +
                "email='" + email + '\'' + ",\n" +
                "password='" + password + '\'' + ",\n" +
                "newsCollections=" + newsCollections + ",\n" +
                "browsingHistory=" + browsingHistory + "\n" +
                '}';
    }
    public enum Role {
        USER,
        ADMIN
    }

}
