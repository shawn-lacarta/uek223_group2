package com.example.demo.domain.userProfile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.security.Principal;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/{id}")
    public ResponseEntity addUserProfile(@RequestBody NewUserProfile userProfile) throws InstanceAlreadyExistsException {
        return ResponseEntity.ok().body(userProfileService.addUserProfile(userProfile));
    }

    @GetMapping("/{id}")
    @PreAuthorize("@userProfileSecurity.hasUserId(#id, #currentUser) || hasAuthority('READ_ALL')")
    public ResponseEntity getOwnUser(@PathVariable UUID id, Principal currentUser) throws NullPointerException {
        return ResponseEntity.ok().body(userProfileService.findById(id, currentUser));
    }

    @GetMapping("/")
    public ResponseEntity<Collection<UserProfile>> getAllUser(Pageable page) {
        return new ResponseEntity(userProfileService.findAllUsers(page), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@userProfileSecurity.hasUserId(#id, #currentUser) || hasAuthority('UPDATE_ALL')")
    public ResponseEntity updateUserProfile(@PathVariable UUID id, Principal currentUser, @RequestBody UserProfile userProfile) {
        return ResponseEntity.ok().body(userProfileService.updateUserProfile(userProfile, id, currentUser));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserProfile(@PathVariable("id") UUID id) throws NullPointerException {
        return ResponseEntity.ok().body(userProfileService.deleteById(id));
    }

}