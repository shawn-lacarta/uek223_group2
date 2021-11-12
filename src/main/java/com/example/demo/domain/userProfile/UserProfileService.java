package com.example.demo.domain.userProfile;


import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
public interface UserProfileService {
    UserProfile findById(UUID id);

    String addUserProfile(NewUserProfile newUserProfile) throws InstanceAlreadyExistsException;

    List<UserProfile> findAllUsers();

    UserProfile updateUserProfile(Principal currentUser, UserProfile userProfile, UUID id) throws InstanceNotFoundException;
}