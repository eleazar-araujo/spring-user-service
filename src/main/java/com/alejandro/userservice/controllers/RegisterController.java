package com.alejandro.userservice.controllers;

import com.alejandro.userservice.rest.error.UserNotFoundException;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class RegisterController {

   private List<User> users;

   @GetMapping("/users")
   public List<User> getUsers(){
       return users;
   }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user){
        users.add(user);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

   @PostConstruct()
   private void populateUsers() {

       List<User> initialColl = Arrays.asList(
         new User(1, "John", "Doe", "john@mail.com"),
         new User(2, "Mike", "Smith", "mike@mail.com"),
         new User(3, "Mary", "Red", "mary@mail.com"),
         new User(4, "Susan", "Green", "susan@mail.com")
       );
       users = new ArrayList<>(initialColl);
   }

   @DeleteMapping("/users/{id}")
   public ResponseEntity deleteUser(@PathVariable int id){
     if (users.stream().noneMatch(u -> u.getId() == id)){
         throw new UserNotFoundException("User with id " + id + " was not found");
     }

     users = users.stream().filter(u -> u.getId() != id).collect(Collectors.toList());
     return new ResponseEntity<>("Deleted " + id, HttpStatus.OK);
   }

   @PutMapping("/users")
   public ResponseEntity updateUser(@RequestBody User user){
       if (users.stream().noneMatch(u -> u.getId() == user.getId())){
           throw new UserNotFoundException("User with id " + user.getId() + " was not found");
       }
       users = users.stream().filter(u -> u.getId() != user.getId()).collect(Collectors.toList());
       users.add(user);
       return new ResponseEntity<>("Updated user successfully ", HttpStatus.OK);
   }


}