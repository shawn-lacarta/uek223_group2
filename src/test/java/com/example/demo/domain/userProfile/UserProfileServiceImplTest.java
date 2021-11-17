package com.example.demo.domain.userProfile;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.management.InstanceAlreadyExistsException;
import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is responsible for testing.
 */
@SpringBootTest
class UserProfileServiceImplTest {

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserProfileRepository userProfileRepository;

    /**
     * This test tests the post method. First, I took the size of the data in the database. After that
     * I added a UserProfile.This means that the variable with which I took the size + 1, should be the
     * same as the current size.
     */
    @Test
    void addUserProfile() {

        int sizeOfUserProfile1 = userProfileRepository.findAll().size();
        try{
            userProfileService.addUserProfile(new NewUserProfile("teststr 32", LocalDate.parse("2012-09-09"), "CH", "0789876543", UUID.fromString("415ab3e8-421a-11ec-81d3-0242ac130003")));
        } catch (InstanceAlreadyExistsException e){
        }
        int sizeOfUserProfile2 = userProfileRepository.findAll().size();
        assertEquals(sizeOfUserProfile1 + 1, sizeOfUserProfile2);
    }


    /**
     * This test checks the Delete method. First we create a new UserProfile. After that I
     * deleted all entries and checked if the size of the database == 0.
     */
    @Test
    void deleteById() {
        try{
            userProfileService.addUserProfile(new NewUserProfile("teststr 32", LocalDate.parse("2012-09-09"), "CH", "0789876543", UUID.fromString("415ab3e8-421a-11ec-81d3-0242ac130003")));
        } catch (InstanceAlreadyExistsException e){
        }
        userProfileRepository.deleteAll();
        assertEquals(true, userProfileRepository.findAll().size() == 0);
    }
}