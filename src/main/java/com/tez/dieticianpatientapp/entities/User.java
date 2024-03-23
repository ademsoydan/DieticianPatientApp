package com.tez.dieticianpatientapp.entities;

import com.tez.dieticianpatientapp.utils.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "user_tckn", unique = true)
    String tckn;

    @Column(name = "password")
    String password;

    @Column(name = "usertype")
    UserType userType;

    public User(String tckn, String password, UserType userType) {
        this.tckn = tckn;
        this.password = password;
        this.userType = userType;
    }


}
