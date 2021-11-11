package com.example.demo.domain.userProfile;
import com.example.demo.domain.appUser.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.Optional;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@Service
public class UserProfileServiceImpl implements UserProfileService {


    private UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile findById(UUID id){
        Optional<UserProfile> optionalUserProfile = this.userProfileRepository.findById(id);
        //return userProfileRepository.findById(id);
        if(optionalUserProfile.isPresent()){
            return optionalUserProfile.get();
        }else{
            return null;
        }
    }
}
