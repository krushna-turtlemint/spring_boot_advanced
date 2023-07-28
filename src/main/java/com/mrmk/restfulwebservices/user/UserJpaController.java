package com.mrmk.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
public class UserJpaController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserDaoService service;


    @GetMapping(path = "/jpa/user")
    public List<User> retrieveUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path = "/jpa/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User addedUser = service.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PostMapping(path = "/jpa/users/{Id}/createPost")
    public ResponseEntity<Post> createPostforUser(@PathVariable int Id, @RequestBody Post post) {
        Optional<User> user = userRepository.findById(Id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + Id);
        }

        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @GetMapping(path = "/jpa/users/{Id}")
    public EntityModel<User> retrieveUser(@PathVariable int Id) {
        Optional<User> user1 = userRepository.findById(Id);
        if (user1.isEmpty()) {
            throw new UserNotFoundException("id:" + Id);
        }

        EntityModel<User> entityModel = EntityModel.of(user1.get());
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;

    }

    @GetMapping(path = "/jpa/users/{Id}/post")
    public List<Post> retrievePostforUser(@PathVariable int Id) {

        Optional<User> user = userRepository.findById(Id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + Id);
        }

        return user.get().getPosts();

    }


    @DeleteMapping(path = "/jpa/delete/{Id}")
    public boolean deleteUser(@PathVariable int Id) {
        return service.deleteOne(Id);
    }

}
