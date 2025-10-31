package com.example.taller_CI.DC.service.Interfaces;

import com.example.taller_CI.DC.model.DTO.Request.RequestRecetaDTO;
import com.example.taller_CI.DC.model.DTO.Response.ResponseRecetaDTO;

import java.util.List;

public interface RecetaService {
    List<ResponseRecetaDTO> obtenerRecetas();

    ResponseRecetaDTO obtenerRecetaPorId(String id);

    List<ResponseRecetaDTO> obtenerRecetasPorChef(String chef);

    List<ResponseRecetaDTO> obtenerRecetasPorTemporada(int temporada);

    List<ResponseRecetaDTO> obtenerRecetasPorIngrediente(String ingrediente);

    void eliminarRecetaPorId(String id);

    ResponseRecetaDTO actualizarRecetaPorId(String id, RequestRecetaDTO recetaDTO);

    ResponseRecetaDTO crearReceta(RequestRecetaDTO recetaDTO);
}
