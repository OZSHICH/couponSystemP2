package com.OzProject.couponSystem.beans;


import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "companies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;


    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "company", orphanRemoval = true)
    @Singular
    @ToString.Exclude
    private List<Coupon> coupons = new ArrayList<>();

}



