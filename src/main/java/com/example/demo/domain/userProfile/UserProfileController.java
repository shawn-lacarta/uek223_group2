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

/**
 * This class is used to represent the endpoints for the userprofile.
 * The endpoints include the GET-, POST-, PUT- and DELETE methods.
 */
@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    public UserProfileController(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    /**
     * This method is responsible for the post endpoint. This can only be
     * used by a user that has a CREATE authority.
     * @param userProfile We pass a userProfile from the NewUserProfile class
     * @return It returns an userprofile from the addUserProfile method in user-
     * ProfileService
     */
    @PostMapping("/")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity addUserProfile(@RequestBody NewUserProfile userProfile) {
        logger.trace("POST USERPROFILES ENDPOINT ACCESSED");
        try {
            return ResponseEntity.ok().body(userProfileService.addUserProfile(userProfile));
        } catch(NullPointerException e){
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (InstanceAlreadyExistsException e){
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    /**
     * This method is responsible for the get (one) endpoint. This can only be
     * used by the current user or a user with READ_ALL authority.
     * @param id The id is used to find the specific userprofile.
     * @param currentUser The currentUser is given to check if he is
     * allowed to see his data. This is done with the principal
     * @return It returns the userprofile from the given id.
     * @throws NullPointerException It may be that the userprofile doesn't exist.
     * That's why we give a NullPointerException with it.
     */
    @GetMapping("/{id}")
    @PreAuthorize("@userProfileSecurity.hasUserId(#id, #currentUser) || hasAuthority('READ_ALL')")
    public ResponseEntity getOwnUser(@PathVariable UUID id, Principal currentUser) {
        logger.trace("GET ONE USERPROFILE ENDPOINT ACCESSED");
        try{
        return ResponseEntity.ok().body(this.userProfileService.findById(id, currentUser));
    } catch(NullPointerException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    /**
     * This method is responsible for the get (all) endpoint. This can only
     * be used by a user that has READ_ALL authority.
     * @param page The page is here so that it does not show all, but only
     * a certain amount of userprofile. Additionally, you can sort the userprofile.
     * @return It returns a certain amount of userprofile which is sorted.
     */
    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ_ALL')")
    public ResponseEntity<Collection<UserProfile>> getAllUser(Pageable page) {
        logger.trace("GET ALL USERPROFILES ENDPOINT ACCESSED");
        return new ResponseEntity(userProfileService.findAllUsers(page), HttpStatus.OK);
    }

    /**
     * This method is responsible for the put endpoint. This can only be
     * used by the current user or a user with UPDATE_ALL authority.
     * @param id The id is given in order to know which user profile
     * should be updated.
     * @param currentUser The currentUser is given to check. whether the
     * user who wants to edit something is allowed.
     * @param userProfile The userProfile must be passed so the userprofile
     * can be edited.
     * @return It returns the updated userprofile
     */
    @PutMapping("/{id}")
    @PreAuthorize("@userProfileSecurity.hasUserId(#id, #currentUser) || hasAuthority('UPDATE_ALL')")
    public ResponseEntity updateUserProfile(@PathVariable UUID id, Principal currentUser, @RequestBody UserProfile userProfile){
        logger.trace("PUT USERPROFILE ENDPOINT ACCESSED");
        try {
            return ResponseEntity.ok().body(userProfileService.updateUserProfile(userProfile, id, currentUser));
        } catch(NullPointerException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    /**
     * This method is responsible for the delete endpoint. This can only be
     * used by a user with DELETE authority.
     * @param id The id is given in order to know which userprofile should
     * be deleted.
     * @return It returns a message if the user is successfully deleted.
     * @throws NullPointerException It may be that the userprofile doesn't exist.
     * That's why we give a NullPointerException with it.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity deleteUserProfile(@PathVariable("id") UUID id) {
        logger.trace("DELETE USERPROFILE ENDPOINT ACCESSED");
        try {
            userProfileService.deleteById(id);
            return ResponseEntity.ok().body("USERPROFILE DELETED");

        } catch (NullPointerException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }


    }

}
