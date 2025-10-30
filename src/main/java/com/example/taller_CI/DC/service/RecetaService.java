package com.example.taller_CI.DC.service;

import com.example.taller_CI.DC.Repository.ChefRepository;
import com.example.taller_CI.DC.Repository.RecetaRepository;
import com.example.taller_CI.DC.Util.Exceptions.ChefNotFoundException;
import com.example.taller_CI.DC.Util.Exceptions.RecetaNotFoundException;
import com.example.taller_CI.DC.Util.Exceptions.TemporadaNullException;
import com.example.taller_CI.DC.Util.Request.RecetaRequestMapper;
import com.example.taller_CI.DC.Util.Response.RecetaResponseMapper;
import com.example.taller_CI.DC.model.Chef;
import com.example.taller_CI.DC.model.DTO.Request.RequestRecetaDTO;
import com.example.taller_CI.DC.model.DTO.Response.ResponseRecetaDTO;
import com.example.taller_CI.DC.model.Receta;
import com.example.taller_CI.DC.model.tipoChef;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RecetaService {
    private final RecetaRepository recetaRepository;
    private final ChefRepository chefRepository;
    private final RecetaRequestMapper recetaRequestMapper;
    private final RecetaResponseMapper recetaResponseMapper;


    public List<ResponseRecetaDTO> obtenerRecetas() {
        List<Receta> recetas = recetaRepository.findAll();

        Map<String, Chef> chefMap = chefRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Chef::getChefId, Function.identity()));

        return recetas.stream()
                .map(r -> recetaResponseMapper.toDTO(r, chefMap.get(r.getChefId())))
                .toList();
    }

    public ResponseRecetaDTO obtenerRecetaPorId(String id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException("Receta no encontrada"));

        Chef chef = chefRepository.findById(receta.getChefId()).orElseThrow(() -> new ChefNotFoundException("Chef no encontrado"));

        return recetaResponseMapper.toDTO(receta, chef);
    }

    public List<ResponseRecetaDTO> obtenerRecetasPorChef(String chef){
        tipoChef tipoEnum = tipoChef.valueOf(chef.toUpperCase());

        List<Chef> chefs = chefRepository.findByTipoChef(tipoEnum);
        List<String> chefIds = chefs.stream()
                .map(Chef::getChefId)
                .toList();

        List<Receta> recetas = recetaRepository.findByChefIdIn((chefIds));


        Map<String, Chef> chefMap = chefs.stream()
                .collect(Collectors.toMap(Chef::getChefId, Function.identity()));

        return recetas.stream()
                .map(r -> recetaResponseMapper.toDTO(r, chefMap.get(r.getChefId())))
                .toList();
    }

    public List<ResponseRecetaDTO> obtenerRecetasPorTemporada(int temporada){
        List<Receta> recetas = recetaRepository.findByTemporada(temporada);

        Map<String, Chef> chefMap = chefRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Chef::getChefId, Function.identity()));

        return recetas.stream()
                .map(r -> recetaResponseMapper.toDTO(r, chefMap.get(r.getChefId())))
                .toList();
    }


    public List<ResponseRecetaDTO> obtenerRecetasPorIngrediente(String ingrediente){
        List<Receta> recetas = recetaRepository.findByIngredientesContaining(ingrediente);

        Map<String, Chef> chefMap = chefRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Chef::getChefId, Function.identity()));

        return recetas.stream()
                .map(r -> recetaResponseMapper.toDTO(r, chefMap.get(r.getChefId())))
                .toList();
    }

    public void eliminarRecetaPorId(String id) {
        recetaRepository.deleteById(id);
    }

    public ResponseRecetaDTO actualizarRecetaPorId(String id, RequestRecetaDTO recetaDTO) {
        Receta recetaExiste = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException("Receta no encontrada"));

        recetaExiste.setTitulo(recetaDTO.getTitulo());
        recetaExiste.setIngredientes(recetaDTO.getIngredientes());
        recetaExiste.setPasosPreparacion(recetaDTO.getPasosPreparacion());
        recetaExiste.setChefId(recetaDTO.getChefId());

        Receta recetaActualizada = recetaRepository.save(recetaExiste);

        Chef chef = chefRepository.findById(recetaActualizada.getChefId()).orElse(null);

        return recetaResponseMapper.toDTO(recetaActualizada, chef);
    }


    public ResponseRecetaDTO crearReceta(RequestRecetaDTO recetaDTO) {
        Chef  chef = chefRepository.findById(recetaDTO.getChefId()).orElseThrow(() -> new ChefNotFoundException("Chef no encontrada"));
        Receta receta = recetaRequestMapper.toEntity(recetaDTO);
        if (chef.getTipoChef() == tipoChef.CONCURSANTE && receta.getTemporada() == null) {
            throw new TemporadaNullException("Los participantes deben decir  la temporada de la receta");
        }
        receta.setFecha(LocalDate.now());
        return recetaResponseMapper.toDTO(recetaRepository.save(receta), chef);
    }



}
