package com.example.demo.domain.appUser;


import com.example.demo.domain.userProfile.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController @RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
//    ADD YOUR ENDPOINT MAPPINGS HERE

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> HomeTest(@PathVariable UUID id){
        return ResponseEntity.ok().body(userService.getUserByID(id));
    }

    @GetMapping("/users")
    public ResponseEntity<Collection<User>> findAll() {
        return new ResponseEntity<Collection<User>>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws InstanceAlreadyExistsException {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

}
