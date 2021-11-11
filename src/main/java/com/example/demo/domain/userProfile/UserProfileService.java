package com.example.demo.domain.userProfile;

import org.springframework.stereotype.Repository;

import javax.management.InstanceAlreadyExistsException;

@Repository
public interface UserProfileService {

    UserProfile addUserProfile(NewUserProfile newUserProfile) throws InstanceAlreadyExistsException;
}
