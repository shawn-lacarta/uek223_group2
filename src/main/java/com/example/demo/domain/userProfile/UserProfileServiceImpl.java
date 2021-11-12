package com.example.demo.domain.userProfile;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
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
    public UserProfile findById(UUID id) {
        Optional<UserProfile> optionalUserProfile = this.userProfileRepository.findById(id);
        //return userProfileRepository.findById(id);
        if (optionalUserProfile.isPresent()) {
            return optionalUserProfile.get();
        } else {
            return null;
        }
    }

    public List<UserProfile> findAllUsers() {
        return userProfileRepository.findAll();
    }


    @Override
    public UserProfile updateUserProfile(Principal currentUser, UserProfile userProfile, UUID id) throws InstanceNotFoundException {
        if (userProfileRepository.existsById(id)) {
            return userProfileRepository.findById(id)
                    .map(userProfile1 -> {
                        userProfile1.setAddress(userProfile.getAddress());
                        userProfile1.setBirthDate(userProfile.getBirthDate());
                        userProfile1.setNationality(userProfile.getNationality());
                        userProfile1.setPhoneNumber(userProfile.getPhoneNumber());
                        return userProfileRepository.save(userProfile);

                    }).orElseGet(() -> {
                        return userProfileRepository.save(userProfile);

                    });


        } else {
            throw new InstanceNotFoundException("not found");
        }

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

}
