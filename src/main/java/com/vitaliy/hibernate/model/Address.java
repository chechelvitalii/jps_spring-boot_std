package com.vitaliy.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;

@Entity
@Table
@Builder
@Getter
@Setter
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

    @Tolerate
    public Address() {
    }
}
