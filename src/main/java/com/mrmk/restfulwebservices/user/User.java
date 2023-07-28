package com.mrmk.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_details")
public class User {
    protected User(){

    }
    @Size(max = 2, message = "Name should have atleast 2 chars")
    private String username;
    @Id
    @GeneratedValue
    private Integer id;
    @Past(message = "Birthdate should be in past")
    private LocalDate birthDate;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public User(String username, Integer id, LocalDate birthDate) {
        this.username = username;
        this.id = id;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", id=" + id + ", birthDate=" + birthDate + '}';
    }

}