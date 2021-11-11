package com.example.demo.domain.userProfile;
import com.example.demo.domain.appUser.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.Optional;
import java.util.UUID;
@Getter
@Setter
@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public Optional<UserProfile> findById(UUID id){
        return userProfileRepository.findById(id);
    }
}
