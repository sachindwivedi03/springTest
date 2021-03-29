package demoApplication.user;


import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {


    @Autowired
    private userDaoList service;

    // retrive all users, GET /users
    @GetMapping (path = "/users")
    public List<User> retriveAllUsers() {
        return service.findAll() ;

    }

    // retrive a specific user, GET /user/{}
    @GetMapping (path = "/user/{id}")
    public EntityModel<User> retriveUser (@PathVariable int id){

          User user =service.findOne(id);
          if (user == null)
              throw new UserNotFoundException("id-" + id);

          // HATEOAS
        // "all users", server path = /users
        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linKTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
        resource.add(linKTo.withRel("all-users"));

        return resource;

    }

    //create a new user
    @PostMapping (path = "/users")
    public ResponseEntity<Object> createUser (@Valid @RequestBody User user){
        User savedUser = service.save(user);

        //created return message with user id
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // delete a user
    // retrive a specific users, GET /user/{}
    @DeleteMapping (path = "/user/{id}")
    private void deleteUser (@PathVariable int id) {

        User user = service.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);

        }
        return;
    }

}
