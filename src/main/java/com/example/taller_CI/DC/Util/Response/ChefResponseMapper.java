package com.example.taller_CI.DC.Util.Response;

import com.example.taller_CI.DC.model.Chef;
import com.example.taller_CI.DC.model.DTO.Response.ResponseChefDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChefResponseMapper {
    ResponseChefDTO toDTO(Chef chef);
}
