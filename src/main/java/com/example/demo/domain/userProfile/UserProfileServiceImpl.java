package com.example.demo.domain.userProfile;
import com.example.demo.domain.appUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        UserProfile userProfile = newUserProfileToUserProfile(newUserProfile);


        if (userProfile.getUser() == null) {
            return "USER NOT FOUND";
        } else if (userProfileRepository.findByUser(userProfile.getUser()) != null) {
            return "USERPROFILE ALREADY EXISTS";
        } else {
            userProfileRepository.save(userProfile);
            return "USERPROFILE CREATED";
        }
    }

    @Override
    public UserProfile updateUserProfile(UserProfile newUserProfile, UUID id, Principal currentUser){
        return userProfileRepository.findById(id)
                .map(updatedUserProfile -> {
                    updatedUserProfile.setAddress(newUserProfile.getAddress());
                    updatedUserProfile.setNationality(newUserProfile.getNationality());
                    updatedUserProfile.setBirthDate(newUserProfile.getBirthDate());
                    updatedUserProfile.setPhoneNumber(newUserProfile.getPhoneNumber());
                    return userProfileRepository.save(updatedUserProfile);
                }).orElseGet(() -> {
                    return userProfileRepository.save(newUserProfile);
                });
    }

    private UserProfile newUserProfileToUserProfile(NewUserProfile newUserProfile) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(userRepository.findById(newUserProfile.getUser_id()).orElse(null));
        userProfile.setNationality(newUserProfile.getNationality());
        userProfile.setAddress(newUserProfile.getAddress());
        userProfile.setPhoneNumber(newUserProfile.getPhoneNumber());
        userProfile.setBirthDate(newUserProfile.getBirthDate());

        return userProfile;
    }

    public List<UserProfile> findAllUsers(){ return userProfileRepository.findAll();}


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
    @Override
    public UserProfile findById(UUID id, Principal currentUser) throws NullPointerException {
        Optional<UserProfile> optionalUserProfile = this.userProfileRepository.findById(id);
        if(optionalUserProfile.isPresent()){
            return optionalUserProfile.get();
        } else {
            throw new NullPointerException();
        }
    }

}
