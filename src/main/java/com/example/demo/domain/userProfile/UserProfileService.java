package com.example.demo.domain.userProfile;


import javax.management.InstanceNotFoundException;
import java.util.Optional;
import java.util.UUID;

public interface UserProfileService {
    public Optional<UserProfile> findById(UUID id);
}
