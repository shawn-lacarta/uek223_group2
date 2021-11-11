package com.example.demo.domain.userProfile;
import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service@RequiredArgsConstructor
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserProfile addUserProfile(NewUserProfile newUserProfile) throws InstanceAlreadyExistsException {
        UserProfile userProfile = new UserProfile(newUserProfile.getId(), newUserProfile.getAddress(),
                newUserProfile.getBirthDate(), newUserProfile.getNationality(),
                newUserProfile.getPhoneNumber(), userRepository.findById(newUserProfile.getUser_id()).orElse(null));

        if (userProfileRepository.existsById(userProfile.getId())){
            throw new InstanceAlreadyExistsException("User profile already exists");
        }else if(userProfile.getUser() == null){
           throw new NullPointerException("User doesn't exist");
        }else{
            return userProfileRepository.save(userProfile);
        }
    }
}
