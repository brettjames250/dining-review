package com.example.diningreview.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "RESTAURANT")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "POSTCODE")
    private String postcode;
    @Column(name = "CUISINE")
    private String cuisine;
}
