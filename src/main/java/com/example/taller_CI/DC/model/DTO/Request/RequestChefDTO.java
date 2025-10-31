package com.example.taller_CI.DC.model.DTO.Request;

import com.example.taller_CI.DC.model.tipoChef;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestChefDTO {
    private tipoChef tipoChef;
    private Integer temporada;
    private String fullName;
}