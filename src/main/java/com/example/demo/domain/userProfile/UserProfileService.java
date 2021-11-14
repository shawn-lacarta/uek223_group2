package com.example.demo.domain.userProfile;

import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.management.InstanceAlreadyExistsException;
import java.security.Principal;
import java.util.UUID;

/**
 * This is the interface of the UserServiceImpl class. Here the
 * method headers are stored to create a better overview.
 */
public interface UserProfileService {
    ResponseEntity findById(UUID id, Principal currentUser);

    ResponseEntity addUserProfile(NewUserProfile newUserProfile) throws InstanceAlreadyExistsException;

    Page<UserProfile> findAllUsers(Pageable page);

    ResponseEntity deleteById(UUID id) throws NullPointerException;

    UserProfile updateUserProfile(UserProfile userProfile, UUID id, Principal currentUser);

}

