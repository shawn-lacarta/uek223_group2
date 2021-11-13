package com.example.demo.domain.userProfile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

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
