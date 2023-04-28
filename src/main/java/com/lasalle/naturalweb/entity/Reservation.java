package com.lasalle.naturalweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name="user_DNI", nullable=false)
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name="therapist_DNI", nullable=false)
    @JsonIgnore
    private Therapist therapist;

}
