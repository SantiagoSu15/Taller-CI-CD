package com.example.taller_CI.DC.service.Interfaces;

import com.example.taller_CI.DC.model.DTO.Request.RequestChefDTO;
import com.example.taller_CI.DC.model.DTO.Response.ResponseChefDTO;

public interface ChefService {
    ResponseChefDTO crearChef(RequestChefDTO dto);

    ResponseChefDTO editarChef(RequestChefDTO dto, String chefId);

    void eliminarChef(String chefId);
}
