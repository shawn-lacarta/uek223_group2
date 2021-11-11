package com.example.demo.domain.userProfile;


import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.UUID;
public interface UserProfileService {
    UserProfile findById(UUID id);
    UserProfile addUserProfile(NewUserProfile newUserProfile) throws InstanceAlreadyExistsException;
    List<UserProfile> findAllUsers();
}
