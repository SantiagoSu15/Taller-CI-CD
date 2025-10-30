package com.example.taller_CI.DC.Repository;

import com.example.taller_CI.DC.model.Chef;
import com.example.taller_CI.DC.model.tipoChef;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChefRepository extends MongoRepository<Chef, String> {
    List<Chef> findByTipoChef(tipoChef tipoChef);
}
