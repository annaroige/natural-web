package com.lasalle.naturalweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String password;
}
