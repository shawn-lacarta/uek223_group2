package com.example.demo.domain.userProfile;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Optional;
import java.util.UUID;
public interface UserProfileService {
    UserProfile findById(UUID id);
    UserProfile addUserProfile(NewUserProfile newUserProfile) throws InstanceAlreadyExistsException;
}
