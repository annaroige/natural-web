package com.lasalle.naturalweb.entity;

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
    @ManyToMany(mappedBy = "scheduleList")
    private List<Therapist> therapistList;

}
