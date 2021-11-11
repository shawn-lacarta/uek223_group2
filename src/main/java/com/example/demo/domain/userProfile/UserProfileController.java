package com.example.demo.domain.userProfile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userprofile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/read")
    @PreAuthorize("hasAuthority('READ_ALL')")
    public ResponseEntity<String> TestRead() {
        return ResponseEntity.ok().body("You Can Read This Page");
    }

    @GetMapping("/readOwn")
    @PreAuthorize("hasAuthority('READ_OWN')")
    public ResponseEntity<String> TestReadOWN() {
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
