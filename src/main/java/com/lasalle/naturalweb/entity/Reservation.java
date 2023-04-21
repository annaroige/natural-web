package com.lasalle.naturalweb.entity;

import jakarta.persistence.*;
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
public class Reservation {

    @Id
    @GeneratedValue
    private Integer id;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name="DNI", nullable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name="DNI", nullable=false)
    private Therapist therapist;

}
