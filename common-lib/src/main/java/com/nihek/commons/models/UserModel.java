package com.nihek.commons.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserModel  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    private boolean accountPublic;

    private String role;

    @ManyToMany
    @JoinTable(
            name = "user_followers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<UserModel> followers;

    @ManyToMany
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private Set<UserModel> follows;

    public UserModel(Long id, String email, String password, String nickname, boolean accountPublic, String role, Set<UserModel> followers, Set<UserModel> follows) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.accountPublic = accountPublic;
        this.role = role;
        this.followers = followers;
        this.follows = follows;
    }

    public UserModel() {
    }

    public static UserModelBuilder builder() {
        return new UserModelBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {return this.password;}

    public String getNickname() {
        return this.nickname;
    }

    public boolean isAccountPublic() {
        return this.accountPublic;
    }

    public String getRole() {
        return this.role;
    }

    public Set<UserModel> getFollowers() {
        return this.followers;
    }

    public Set<UserModel> getFollows() {
        return this.follows;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAccountPublic(boolean accountPublic) {
        this.accountPublic = accountPublic;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFollowers(Set<UserModel> followers) {
        this.followers = followers;
    }

    public void setFollows(Set<UserModel> follows) {
        this.follows = follows;
    }


    public static class UserModelBuilder {
        private Long id;
        private String email;
        private String password;
        private String nickname;
        private boolean accountPublic;
        private String role;
        private Set<UserModel> followers;
        private Set<UserModel> follows;

        UserModelBuilder() {
        }

        public UserModelBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserModelBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserModelBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserModelBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserModelBuilder accountPublic(boolean accountPublic) {
            this.accountPublic = accountPublic;
            return this;
        }

        public UserModelBuilder role(String role) {
            this.role = role;
            return this;
        }

        public UserModelBuilder followers(Set<UserModel> followers) {
            this.followers = followers;
            return this;
        }

        public UserModelBuilder follows(Set<UserModel> follows) {
            this.follows = follows;
            return this;
        }

        public UserModel build() {
            return new UserModel(this.id, this.email, this.password, this.nickname, this.accountPublic, this.role, this.followers, this.follows);
        }

        public String toString() {
            return "UserModel.UserModelBuilder(id=" + this.id + ", email=" + this.email + ", password=" + this.password + ", nickname=" + this.nickname + ", accountPublic=" + this.accountPublic + ", role=" + this.role + ", followers=" + this.followers + ", follows=" + this.follows + ")";
        }
    }
}
