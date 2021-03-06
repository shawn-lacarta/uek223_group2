package com.example.demo.domain.userProfile;

import com.example.demo.domain.appUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InstanceAlreadyExistsException;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

/**
 * This is the service class for userprofile. Here all the logic is determined, which
 * is then passed on to the controller class.
 */
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

    /**
     * This method is responsible for adding a userprofile. First the
     * method, which is the whole creating process is stored in userProfile.
     * after that makes the following checks: User exists, User already has a
     * UserProfile and User Profile created successfully.
     *
     * @param newUserProfile We must pass an userprofile so that we can create one
     * @return A response entity returns with the appropriate HTTP status and a message
     * @throws InstanceAlreadyExistsException Since it may be that the user already
     * exists, we need to pass the InstanceAlreadyExistsException along.
     */
    @Override
    public UserProfile addUserProfile(NewUserProfile newUserProfile) throws InstanceAlreadyExistsException, NullPointerException {

        UserProfile userProfile = newUserProfileToUserProfile(newUserProfile);

        if (userProfile.getUser() == null) {
            throw new NullPointerException("USER NOT FOUND");
        } else if (userProfileRepository.findByUser(userProfile.getUser()) != null) {
            throw new InstanceAlreadyExistsException("USER ALREADY HAS USERPROFILE");
        } else {
            return userProfileRepository.save(userProfile);
        }
    }

    /**
     * this method is responsible for updating a userprofile. First it maps
     * through all fields and replaces this with the appropriate input.  Of course,
     * it has to check first if the id exists at all. If everything fits the method
     * replaces the new input with the old.
     *
     * @param newUserProfile We must pass an userprofile so that we can create one
     * @param id             We need to pass the id to know which user to update
     * @param currentUser    We need to pass the currentUser to check if
     *                       he can update his data
     * @return It returns the updated userprofile.
     */
    @Override
    public UserProfile updateUserProfile(UserProfile newUserProfile, UUID id, Principal currentUser) throws NullPointerException {
        Optional<UserProfile> optionalUserProfile = this.userProfileRepository.findById(id);

        if(optionalUserProfile.isEmpty()) {
            throw new NullPointerException("USERPROFILE DOESN'T EXIST");
        } else if(!userProfileRepository.existsById(id)) {
            throw new NullPointerException("USER DOESN'T EXIST");

        }else
        {
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
    }

    /**
     * This method is responsible to translate the userprofile with the newUserProfile. Since in
     * the userprofile class you have to provide a whole user, we have created a newUserProfile
     * class and linked it together. This method is used to make new entries. This simplifies an
     * entry, because the user only has to enter the user_id and not a whole user.
     *
     * @param newUserProfile We must pass an userprofile so that we can create one
     * @return It returns the userprofile
     */
    private UserProfile newUserProfileToUserProfile(NewUserProfile newUserProfile) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(userRepository.findById(newUserProfile.getUser_id()).orElse(null));
        userProfile.setNationality(newUserProfile.getNationality());
        userProfile.setAddress(newUserProfile.getAddress());
        userProfile.setPhoneNumber(newUserProfile.getPhoneNumber());
        userProfile.setBirthDate(newUserProfile.getBirthDate());

        return userProfile;
    }

    /**
     * This method is responsible to find all user.
     *
     * @param page This is responsible to show only a few user and to sort it.
     * @return It returns a few user (pageable)
     */
    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Page<UserProfile> findAllUsers(Pageable page) {
        return userProfileRepository.findAll(page);
    }

    /**
     * This method is responsible to delete an userprofile.
     *
     * @param id The id is to know which user should be deleted.
     * @return A response entity returns with the appropriate HTTP status and a message.
     * @throws NullPointerException It may be that the userprofile doesn't exist.
     *                              That's why we give a NullPointerException with it.
     */
    @Override
    @Transactional(rollbackFor = NullPointerException.class)
    public UserProfile deleteById(UUID id) throws NullPointerException {
        Optional<UserProfile> optionalUserProfile = this.userProfileRepository.findById(id);
        if (optionalUserProfile.isEmpty()) {
            throw new NullPointerException("USERPROFILE DOESN'T EXIST");
        }else {
            userProfileRepository.deleteById(id);
        }


        return null;
    }

    /**
     * This method is to find a userprofile by the id. It checks if the user is existing or not.
     * @param id The id is to know which user should be deleted.
     * @param currentUser The currentuser is needed to check if he can view his data
     * @return It returns the userprofile of the specific user or a message "User not found":
     * @throws NullPointerException It may be that the userprofile doesn't exist.
     * That's why we give a NullPointerException with it.
     */
    @Override
    @Transactional(rollbackFor = NullPointerException.class)
    public UserProfile findById(UUID id, Principal currentUser) throws NullPointerException {
        Optional<UserProfile> optionalUserProfile = this.userProfileRepository.findById(id);
        if (optionalUserProfile.isPresent()) {
            return optionalUserProfile.get();
        } else {
            throw new NullPointerException("USERPROFILE NOT FOUND");
        }
    }

}
