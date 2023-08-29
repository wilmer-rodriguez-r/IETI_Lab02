package org.adaschool.api.controller.user;

import org.adaschool.api.exception.UserNotFoundException;
import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.UserDto;
import org.adaschool.api.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        User created = usersService.save(user);
        URI createdUserUri = URI.create("");
        return ResponseEntity.created(createdUserUri).body(created);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.all();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        Optional<User> user = usersService.findById(id);
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        } else throw new UserNotFoundException(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        Optional<User> user = usersService.findById(id);
        if(user.isPresent()){
            User saved = usersService.save(user.get());
            return ResponseEntity.ok(saved);
        } else throw new UserNotFoundException(id);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id, @RequestBody(required=false) UserDto userDto) {
        Optional<User> user = usersService.findById(id);
        if(user.isPresent()){
            User deleting = new User(userDto);
            usersService.deleteById(id);
            return ResponseEntity.ok().build();
        } else throw new UserNotFoundException(id);
    }
}
