package com.lasalle.naturalweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Therapist {

    @Id
    @GeneratedValue
    private Integer id;
    private String dni;
    private String name;
    private String surname1;
    private String surname2;
    private String birthday;
    private String address;
    private String email;
    private String phone;
    private String therapyDescription;
    @OneToMany(mappedBy = "therapist")
    private List<Schedule> scheduleList;

    @OneToMany(mappedBy="therapist")
    private List<Reservation> therapistReservation;

}
