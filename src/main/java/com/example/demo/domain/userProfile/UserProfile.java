package com.example.demo.domain.userProfile;

import com.example.demo.domain.appUser.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "user_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "address")
    private String address;
    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

