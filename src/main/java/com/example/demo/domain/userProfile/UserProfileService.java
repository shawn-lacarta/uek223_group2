package com.example.demo.domain.userProfile;


import org.springframework.http.ResponseEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.management.InstanceAlreadyExistsException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
public interface UserProfileService {
    UserProfile findById(UUID id, Principal currentUser);
    ResponseEntity addUserProfile(NewUserProfile newUserProfile) throws InstanceAlreadyExistsException;
    Page<UserProfile> findAllUsers(Pageable page);
    ResponseEntity deleteById(UUID id) throws NullPointerException;
    UserProfile updateUserProfile(UserProfile userProfile, UUID id, Principal currentUser);

}

