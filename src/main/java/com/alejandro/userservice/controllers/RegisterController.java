package com.alejandro.userservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api")
public class RegisterController {

   private List<User> users;

   @GetMapping("/users")
   public List<User> testMethod(){
       return users;
   }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user){
        users.add(user);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

   @PostConstruct()
   private void populateUsers() {
       users = Arrays.asList(
         new User(1, "John", "Doe", "john@mail.com"),
         new User(1, "Mike", "Smith", "mike@mail.com"),
         new User(1, "Mary", "Red", "mary@mail.com"),
         new User(1, "Susan", "Green", "susan@mail.com")
       );
   }

}