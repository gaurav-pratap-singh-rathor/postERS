package com.gaurav.rest.webservices.postERS.user;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 10)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY) // FetchType.LAZY means it will not fetch user details when we fetch a post.
    @JsonIgnore     //user will ignore when we fetch a post.
    private User user;     //this will create a releation between the user and the post.
    //Many Post will have one user relation.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //user methods
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", description=" + description + "]";
    }



}
