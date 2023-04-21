package com.lasalle.naturalweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String DNI;
    private String name;
    private String surname1;
    private String surname2;
    private String birthday;
    private String address;
    private String email;
    private String phone;

    @OneToMany(mappedBy="user")
    private List<Reservation> userReservation;
}
