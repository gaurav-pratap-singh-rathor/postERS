package com.gaurav.rest.webservices.postERS.user;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.gaurav.rest.webservices.postERS.jpa.PostRepository;
import com.gaurav.rest.webservices.postERS.jpa.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



import jakarta.validation.Valid;
/*
 New method with JPA
 From UserRepository we have to call JpaRepository further it call CrudRepository (Which contains all save()find ,update methods)
  then JpaRepository will automatically  save data in user object by createing objects of type user for different user.
*/



@RestController     //Main contoller for user when we are using the Persistance(JPA) Database.
public class UserJpaResource {

    private UserRepository userRepository;

    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) { //It is a Constructor injection. we are injecting user and postRepository.
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    //GET/Users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }


    //http://localhost:8080/users

    //EntityModel
    //WebMvcLinkBuilder


    //GET/Users/id
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {  // here the input parameter to retrieveUser is pathvarible Id.
        Optional<User> user = userRepository.findById(id); //here first we are finding user by ID in uri and storing in user variable.

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        EntityModel<User> entityModel = EntityModel.of(user.get()); //getting user details and storing in entitymodal variable.

        WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel; //entityModel variable has user details.
    }


    // DELETE/User/id
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    // GET/User/id/Post
    @GetMapping("/jpa/users/{id}/posts") //To GET the post data 2 SQl quries will execute in background.
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id); //here first we are finding user by ID in uri and storing in user variable.

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        return user.get().getPosts();  //then user.get().getPosts will get post for that specific user.
        //user Getter method in post.java class.

    }
    //   POST/Users
    @PostMapping("/jpa/users")          //@valid ensure that the empty or random values should not be entered in post request. It check validation that we defined in user.java class
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {  //Here we are creating createUser Function of type User class [ResponseEntity<User>] .
        // This function we get the user(variable) as parameter of Type User class to the function. To achieve this POST mapping we have use @RequestBody annotation.
        // we enter all in information of type User in browser and that data will stores in the variable user.

        User savedUser = userRepository.save(user);    // variable savedUser will stores the return value of Type User that we get from -  userRepository.save(user);
//In Location Header we get a new created user link
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()   //it will Return the uri of the created new user in url link with id of new user
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();  // built in method to return Created 201 message response instead of 200
    }

    // POST/User/id/Post
    @PostMapping("/jpa/users/{id}/posts")        //To POST the post data 3 SQl quries will execute.                            //Post must be valid JSON.
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {  // Here we are getting 2 input parameter 1. id is a @PathVarible (We get id value from path URI) 2. post is data of type Post that the user will enter to send to our API.For this we need to use @RequestBody annotation.
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        post.setUser(user.get());   //user Setter method in post.java class.

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }


}
