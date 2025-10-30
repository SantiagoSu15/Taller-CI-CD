package com.example.taller_CI.DC.model.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseRecetaDTO {

    private String titulo;
    private List<String> ingredientes;
    private List<String> pasosPreparacion;
    private Integer temporada;
    private LocalDate fecha;
    private String nombreChef;
    private String tipoChef;
}
