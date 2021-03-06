package com.dailynews.Newsdaily.domen;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="n_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String email;
    private String fullName;
    @NotNull
    private String username;
    private String password;

    private boolean userStatus;
    private Date created;
    private Date updated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;
   @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<NewsVoting> newsVoting;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<NewsComments> newsComments;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<NewsCommentReplies> newsCommentReplies;
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private UserImage userImage;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.userStatus = user.getUserStatus();
        this.roles = user.getRoles();
        this.fullName = user.getFullName();
       this.userImage = user.getUserImage();
    }

    public UserImage getUserImage() {
        return userImage;
    }

   public void setUserImage(UserImage userImage) {
        this.userImage = userImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   /* public String getConfirmPassword() {
        return confirmPassword;
    }*/

   /* public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }*/

    public boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }



    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

   public List<NewsVoting> getNewsVoting() {
        return newsVoting;
    }

   public void setNewsVoting(List<NewsVoting> newsVoting) {
        this.newsVoting = newsVoting;
    }

    public List<NewsComments> getNewsComments() {
        return newsComments;
    }

    public void setNewsComments(List<NewsComments> newsComments) {
        this.newsComments = newsComments;
    }

    public List<NewsCommentReplies> getNewsCommentReplies() {
        return newsCommentReplies;
    }

    public void setNewsCommentReplies(List<NewsCommentReplies> newsCommentReplies) {
        this.newsCommentReplies = newsCommentReplies;
    }
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}