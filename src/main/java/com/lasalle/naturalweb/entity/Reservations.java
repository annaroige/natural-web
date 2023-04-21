package com.lasalle.naturalweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservations {

    @Id
    @GeneratedValue
    private Integer id;
    private String userDni;
    private String therapistDni;
    private LocalDateTime date;

    @OneToOne(mappedBy = "userReservations")
    private User user;

    @OneToOne(mappedBy = "therapistReservations")
    private Therapist therapist;

}
