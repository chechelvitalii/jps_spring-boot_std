package com.vitaliy.hibernate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(unique = true)
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
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
