package com.example.diningreview.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "POST_CODE")
    private String postCode;
    @Column(name = "PEANUT_ALLERGY")
    private boolean isPeanutAllergy;
    @Column(name = "EGG_ALLERGY")
    private boolean isEggAllergy;
    @Column(name = "DAIRY_ALLERGY")
    private boolean isDairyAllergy;



}
