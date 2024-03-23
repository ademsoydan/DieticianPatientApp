package com.tez.dieticianpatientapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "dietician")
@Entity
@Getter
@Setter
public class Dietician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tckn", referencedColumnName = "user_tckn")
    User user;

    public Dietician(String firstName, String lastName, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }
}
