package com.example.demo.domain.userProfile;

import com.example.demo.domain.appUser.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity addUserProfile(@RequestBody NewUserProfile userProfile) throws InstanceAlreadyExistsException {
        return ResponseEntity.ok().body(userProfileService.addUserProfile(userProfile));
    }
    @GetMapping("/{id}")
    @PreAuthorize("@userProfileSecurity.hasUserId(#id, #currentUser) || hasAuthority('READ_ALL')")
    public ResponseEntity<UserProfile> getOwnUser(@PathVariable UUID id, Principal currentUser) throws NullPointerException {
        return ResponseEntity.ok().body(this.userProfileService.findById(id, currentUser));
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ_ALL')")
    public ResponseEntity<Collection<UserProfile>> getAllUser() {
        return new ResponseEntity<>(userProfileService.findAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@userProfileSecurity.hasUserId(#id, #currentUser) || hasAuthority('UPDATE_ALL')")
    public ResponseEntity<UserProfile> updateUserProfile(@PathVariable UUID id, Principal currentUser, @RequestBody UserProfile userProfile){
        return ResponseEntity.ok().body(userProfileService.updateUserProfile(userProfile, id, currentUser));

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity deleteUserProfile(@PathVariable("id") UUID id) throws NullPointerException{
        return ResponseEntity.ok().body(userProfileService.deleteById(id));
    }

}
