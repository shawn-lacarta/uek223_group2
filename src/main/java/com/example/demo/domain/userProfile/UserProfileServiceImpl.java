package com.example.demo.domain.userProfile;
import com.example.demo.domain.appUser.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.management.InstanceAlreadyExistsException;
import javax.transaction.Transactional;

@Service@RequiredArgsConstructor
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfile addUserProfile(UserProfile userProfile) throws InstanceAlreadyExistsException {
        if (userProfileRepository.existsById(userProfile.getId())){
            throw new InstanceAlreadyExistsException("User profile already exists");
        }else {
            //userProfile.setUser();
            return userProfileRepository.save(userProfile);
        }
    }
}
