package com.example.demo.domain.userProfile;
import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.management.InstanceAlreadyExistsException;
import java.security.Principal;
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
    public String addUserProfile(NewUserProfile newUserProfile) throws InstanceAlreadyExistsException {
        UserProfile userProfile = new UserProfile(newUserProfile.getId(), newUserProfile.getAddress(),
                newUserProfile.getBirthDate(), newUserProfile.getNationality(),
                newUserProfile.getPhoneNumber(), userRepository.findById(newUserProfile.getUser_id()).orElse(null));
        if (userProfileRepository.existsById(userProfile.getId())){
            return"USERPROFILE ALREADY EXIST";
        }else if(userProfile.getUser() == null){
            return"USERPROFILE NOT FOUND";
        }else{
            userProfileRepository.save(userProfile);
            return "USERPROFILE CREATED";
        }
    }

    public List<UserProfile> findAllUsers(){ return userProfileRepository.findAll();}

    @Override
    public UserProfile findById(UUID id, Principal currentUser) throws NullPointerException {
        Optional<UserProfile> optionalUserProfile = this.userProfileRepository.findById(id);
        if(optionalUserProfile.isPresent()){
           return optionalUserProfile.get();
        } else {
            throw new NullPointerException();
        }
    }
    @Override
    public String deleteById(UUID id) throws NullPointerException{
        Optional<UserProfile> optionalUserProfile = this.userProfileRepository.findById(id);
        if (optionalUserProfile.isEmpty()) {
            throw new NullPointerException("USER NOT FOUND");
        } else {
            userProfileRepository.deleteById(id);
            return "USER DELETED";
        }
    }

}
