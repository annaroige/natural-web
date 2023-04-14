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
public class PersonalCredentials {

    @Id
    private String DNI;
    private String username;
    private String password;
    private String role;

}
