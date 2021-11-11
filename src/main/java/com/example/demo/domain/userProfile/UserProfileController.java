package com.example.demo.domain.userProfile;

import com.example.demo.domain.appUser.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
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
    public ResponseEntity<UserProfile> addUserProfile(@RequestBody NewUserProfile userProfile) throws  InstanceAlreadyExistsException {
        return ResponseEntity.ok().body(userProfileService.addUserProfile(userProfile));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_OWN')")
    public ResponseEntity<UserProfile> getOwnUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(this.userProfileService.findById(id));
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ_ALL')")
    public ResponseEntity<Collection<UserProfile>> getAllUser() {
        return new ResponseEntity<Collection<UserProfile>>(userProfileService.findAllUsers(), HttpStatus.OK);
    }
}
