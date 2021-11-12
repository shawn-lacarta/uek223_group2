package com.example.demo.domain.security;

import com.example.demo.domain.appUser.UserRepository;
import com.example.demo.domain.userProfile.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.UUID;

@Component("userProfileSecurity")
public class UserProfileAuthorization {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserProfileRepository userProfileRepository;

    public boolean hasUserId(UUID id, Principal currentUser){
        UUID idCurrent = userProfileRepository.findByUser(userRepository.findByUsername(currentUser.getName())).getId();
        if(idCurrent.equals(id))
            return true;
        return false;
    }

}
