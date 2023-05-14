package com.lasalle.naturalweb.dto;

import lombok.Data;
import java.time.LocalTime;

@Data
public class Disponibility {

    private LocalTime time;
    private boolean disponible;
}
