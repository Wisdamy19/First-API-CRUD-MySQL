package com.firstproject.API.MYSQL.Controller;

import com.firstproject.API.MYSQL.Model.User;
import com.firstproject.API.MYSQL.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id")long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User userobj = userRepository.save(user);
        return new ResponseEntity<>(userobj, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserByID(@PathVariable(value = "id")long id, @RequestBody User userDetails){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User userUpdate = userOptional.get();
            userUpdate.setUsername(userDetails.getUsername());
            userUpdate.setPassword(userDetails.getPassword());

            userRepository.save(userUpdate);

            return ResponseEntity.ok(userUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserByID(@PathVariable(value = "id")long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.ok().body("User: " + id + " deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}


