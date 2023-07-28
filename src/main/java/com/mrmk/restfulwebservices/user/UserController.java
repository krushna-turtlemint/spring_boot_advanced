package com.mrmk.restfulwebservices.user;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
public class UserController {
    @Autowired
    private UserDaoService service;


    @GetMapping(path = "/user")
    public List<User> retrieveUsers() {
        return service.fetchUsers();
    }

    @PostMapping(path = "/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User addedUser = service.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @GetMapping(path = "/users/{Id}")
    public EntityModel<User> retrieveUser(@PathVariable int Id) {

        User user = service.findOne(Id);
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;

    }


    @DeleteMapping(path = "/delete/{Id}")
    public boolean deleteUser(@PathVariable int Id) {
        System.out.println("---------" + Id);
        return service.deleteOne(Id);
    }

}
