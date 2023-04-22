package com.lasalle.naturalweb.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Disponibility {

    private LocalDate date;
    private LocalTime time;
    private boolean disponible;
}
