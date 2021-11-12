package com.example.demo.domain.userProfile;

import com.example.demo.domain.appUser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID>{
    UserProfile findByUser(User user);

}
