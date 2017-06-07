package com.vitaliy.hibernate.model;

import lombok.Builder;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Builder
@ToString(exclude = "user")
public class Address {
    @Id
    @Column(unique = true)
    private Integer id;
    @Column
    private String street;
    @Column
    private String buildNumber;
    @ManyToOne
    private User user;
}
