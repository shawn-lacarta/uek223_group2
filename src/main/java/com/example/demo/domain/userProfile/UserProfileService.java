package com.example.demo.domain.userProfile;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Optional;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
public interface UserProfileService {
    UserProfile findById(UUID id, Principal currentUser) throws NullPointerException;
    String addUserProfile(NewUserProfile newUserProfile) throws InstanceAlreadyExistsException;
    List<UserProfile> findAllUsers();
    String deleteById(UUID id) throws NullPointerException;


}
