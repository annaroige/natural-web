package com.lasalle.naturalweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer dayOfWeek;
    private Integer session;
    @ManyToOne
    @JoinColumn(name="therapist_dni", nullable=false)
    @JsonIgnore
    private Therapist therapist;

}
