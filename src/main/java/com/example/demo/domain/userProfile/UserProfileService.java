package com.example.demo.domain.userProfile;


import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.Optional;
import java.util.UUID;

public interface UserProfileService {
    UserProfile findById(UUID id);
}
