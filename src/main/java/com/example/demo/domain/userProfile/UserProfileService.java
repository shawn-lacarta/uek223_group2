package com.example.demo.domain.userProfile;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.InstanceAlreadyExistsException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface UserProfileService {
    UserProfile findById(UUID id, Principal currentUser);
    String addUserProfile(NewUserProfile newUserProfile) throws InstanceAlreadyExistsException;
    Page<UserProfile> findAllUsers(Pageable page);
    String deleteById(UUID id) throws NullPointerException;
    UserProfile updateUserProfile(UserProfile userProfile, UUID id, Principal currentUser);

}

