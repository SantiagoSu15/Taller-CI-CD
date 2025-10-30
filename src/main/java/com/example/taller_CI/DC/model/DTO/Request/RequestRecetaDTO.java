package com.example.taller_CI.DC.model.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestRecetaDTO {
    @NotBlank(message = "El titulo de la receta es obligatorio")
    private String titulo;

    @NotEmpty(message = "Debes decir al menos un ingrediente")
    private List<@NotBlank(message = "El nombre del ingrediente no puede estar vacio") String> ingredientes;

    @NotEmpty(message = "Debe especificar los pasos de preparación")
    private List<@NotBlank(message = "Ningun paso de preparacion no puede estar vacio") String> pasosPreparacion;

    @NotBlank(message = "Debes especificar el ID del chef")
    private String chefId;

    private Integer temporada;
}
