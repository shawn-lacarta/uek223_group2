package com.example.demo.domain.userProfile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Since in the userprofile class you have to provide a whole user, we have created a newUserProfile
 * class. With this class we can implement implementations more easily, because we don't have to
 * provide a whole user each time, but only the user_id
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewUserProfile {
    private String address;
    private LocalDate birthDate;
    private String nationality;
    private String phoneNumber;
    private UUID user_id;
}
