package com.example.taller_CI.DC.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "Receta")
public class Receta {
    @Id
    private String id;
    private String titulo;
    private List<String> ingredientes;
    private List<String> pasosPreparacion;
    private String chefId;
    private Integer temporada;
    private LocalDate fecha;



}
