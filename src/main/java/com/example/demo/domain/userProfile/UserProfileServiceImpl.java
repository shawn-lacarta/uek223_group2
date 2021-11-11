package com.example.demo.domain.userProfile;
import com.example.demo.domain.appUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }
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

    public List<UserProfile> findAllUsers(){ return userProfileRepository.findAll();}
}
