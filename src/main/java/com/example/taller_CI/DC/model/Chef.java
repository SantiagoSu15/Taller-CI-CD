package com.example.taller_CI.DC.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Chef")
public class Chef {
    @Id
    private String chefId;
    private tipoChef tipoChef;
    private Integer temporada;
    private String fullName;
}
