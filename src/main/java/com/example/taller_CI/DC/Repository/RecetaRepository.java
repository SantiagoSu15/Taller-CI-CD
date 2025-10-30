package com.example.taller_CI.DC.Repository;

import com.example.taller_CI.DC.model.Receta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface RecetaRepository extends MongoRepository<Receta, String> {
    List<Receta> findByChefId(String chefId);
    List<Receta> findByTemporada(int temporadaId);
    List<Receta> findByIngredientesContaining(String ingrediente);

    List<Receta> findByChefIdIn(Collection<String> chefIds);
}
