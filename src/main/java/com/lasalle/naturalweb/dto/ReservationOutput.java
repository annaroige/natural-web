package com.lasalle.naturalweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationOutput {

    private String userName;
    private String therapistName;
    private String date;

}
