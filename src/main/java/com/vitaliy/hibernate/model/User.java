package com.vitaliy.hibernate.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(unique = true)
    private Integer inn;
    @Column
    private String name;
    @Column
    private String surname;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Address> addresses;

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }
}
