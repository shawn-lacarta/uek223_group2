package com.example.demo.domain.userProfile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;

@RestController
@RequestMapping("/userprofile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping("/addUserProfile")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<UserProfile> addUserProfile(@RequestBody NewUserProfile userProfile) throws InstanceAlreadyExistsException {
        return ResponseEntity.ok().body(userProfileService.addUserProfile(userProfile));
    }


}
