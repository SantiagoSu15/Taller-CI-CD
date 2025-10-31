package com.example.taller_CI.DC.model.DTO.Response;

import com.example.taller_CI.DC.model.tipoChef;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChefDTO {
    private String chefId;
    private tipoChef tipoChef;
    private Integer temporada;
    private String fullName;
}