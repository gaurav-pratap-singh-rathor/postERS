package com.gaurav.rest.webservices.postERS.jpa;
import com.gaurav.rest.webservices.postERS.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Integer> {

}
