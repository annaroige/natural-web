package com.lasalle.naturalweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationInput {

    private String userDNI;
    private String therapistDNI;
    private String date;

}
