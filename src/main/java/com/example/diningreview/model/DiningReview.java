package com.example.diningreview.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DINING_REVIEW")
@Getter
@Setter
@NoArgsConstructor
public class DiningReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "REVIEWED_BY")
    private String reviewedBy;
    @Column(name = "RESTAURANT_ID")
    private Long restaurantId;
    @Column(name = "PEANUT_SCORE")
    private Integer peanutScore;
    @Column(name = "EGG_SCORE")
    private Integer eggScore;
    @Column(name = "DAIRY_SCORE")
    private Integer dairyScore;
    @Column(name = "COMMENTARY")
    private String commentary;
    @Column(name = "REVIEW_STATUS")
    @Enumerated(EnumType.STRING)
    private AdminReviewStatus status;

}
