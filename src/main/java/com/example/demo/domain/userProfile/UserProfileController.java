package com.example.demo.domain.userProfile;

import com.example.demo.domain.appUser.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.management.InstanceNotFoundException;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/userprofile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_OWN')")
    public ResponseEntity<Optional<UserProfile>> getOwnUser(@PathVariable("id") UUID id, UserProfileService userProfileService) throws InstanceNotFoundException {
        return ResponseEntity.ok().body(userProfileService.findById(id));
    }

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAuthority('READ_OWN')")
    public ResponseEntity<UserProfile> getAllUsers(UserProfile userProfile){
        return ResponseEntity.ok().body(userProfile);
    }

    @GetMapping("/read")
    @PreAuthorize("hasAuthority('READ_ALL')")
    public ResponseEntity<String> TestRead() {
        return ResponseEntity.ok().body("You Can Read This Page");
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<String> TestCreate() {
        return ResponseEntity.ok().body("You Can Create On This Page");
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> TestDelete() {
        return ResponseEntity.ok().body("You Can Delete On This Page");
    }

    @GetMapping("/update")
    @PreAuthorize("hasAuthority('UPDATE_ALL')")
    public ResponseEntity<String> TestUpdate() {
        return ResponseEntity.ok().body("You Can Update On This Page");
    }



}
