package com.example.demo.domain.userProfile;

import com.example.demo.domain.appUser.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    public UserProfileController(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<String> addUserProfile(@RequestBody NewUserProfile userProfile) throws InstanceAlreadyExistsException {
        logger.trace("POST USERPROFILES ENDPOINT ACCESSED");
        return ResponseEntity.ok().body(userProfileService.addUserProfile(userProfile));
    }
    @GetMapping("/{id}")
    @PreAuthorize("@userProfileSecurity.hasUserId(#id, #currentUser) || hasAuthority('READ_ALL')")
    public ResponseEntity<UserProfile> getOwnUser(@PathVariable UUID id, Principal currentUser) throws NullPointerException {
        logger.trace("GET ONE USERPROFILE ENDPOINT ACCESSED");
        return ResponseEntity.ok().body(this.userProfileService.findById(id, currentUser));
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ_ALL')")
    public ResponseEntity<Collection<UserProfile>> getAllUser(Pageable page) {
        logger.trace("GET ALL USERPROFILES ENDPOINT ACCESSED");
        return new ResponseEntity(userProfileService.findAllUsers(page), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@userProfileSecurity.hasUserId(#id, #currentUser) || hasAuthority('UPDATE_ALL')")
    public ResponseEntity<UserProfile> updateUserProfile(@PathVariable UUID id, Principal currentUser, @RequestBody UserProfile userProfile){
        logger.trace("PUT USERPROFILE ENDPOINT ACCESSED");
        return ResponseEntity.ok().body(userProfileService.updateUserProfile(userProfile, id, currentUser));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> deleteUserProfile(@PathVariable("id") UUID id) throws NullPointerException{
        logger.trace("DELETE USERPROFILE ENDPOINT ACCESSED");
        return ResponseEntity.ok().body(userProfileService.deleteById(id));
    }

}
