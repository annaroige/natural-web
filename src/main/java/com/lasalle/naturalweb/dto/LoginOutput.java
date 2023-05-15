package com.lasalle.naturalweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginOutput {

    private Boolean allow;
    private String role;
    private String dni;

}
