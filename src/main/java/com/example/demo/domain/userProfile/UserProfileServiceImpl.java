package com.example.demo.domain.userProfile;

import com.example.demo.domain.appUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.security.Principal;
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
    public ResponseEntity addUserProfile(NewUserProfile newUserProfile) throws InstanceAlreadyExistsException {

        UserProfile userProfile = newUserProfileToUserProfile(newUserProfile);

        if (userProfile.getUser() == null) {
            return ResponseEntity.status(404).body("USER NOT FOUND");
        } else if (userProfileRepository.findByUser(userProfile.getUser()) != null) {
            return ResponseEntity.status(409).body("USER ALREADY HAS USERPROFILE");
        } else {
            userProfileRepository.save(userProfile);
            return ResponseEntity.status(201).body("USERPROFILE CREATED");
        }
    }

    @Override
    public UserProfile updateUserProfile(UserProfile newUserProfile, UUID id, Principal currentUser) {
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

    @Override
    public Page<UserProfile> findAllUsers(Pageable page) {
        return userProfileRepository.findAll(page);
    }

    @Override
    public ResponseEntity deleteById(UUID id) throws NullPointerException {
        Optional<UserProfile> optionalUserProfile = this.userProfileRepository.findById(id);
        if (optionalUserProfile.isEmpty()) {
            return ResponseEntity.status(404).body("USER NOT FOUND");
        } else {
            userProfileRepository.deleteById(id);
            return ResponseEntity.status(200).body("USER DELETED");
        }
    }

    @Override
    public ResponseEntity findById(UUID id, Principal currentUser) throws NullPointerException {
        Optional<UserProfile> optionalUserProfile = this.userProfileRepository.findById(id);
        if (optionalUserProfile.isPresent()) {
            return ResponseEntity.ok(optionalUserProfile.get());
        } else {
            return ResponseEntity.status(404).body("USER NOT FOUND");
        }
    }

}
