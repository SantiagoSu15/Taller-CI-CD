package com.example.taller_CI.DC.Util.Response;

import com.example.taller_CI.DC.model.Chef;
import com.example.taller_CI.DC.model.DTO.Response.ResponseRecetaDTO;
import com.example.taller_CI.DC.model.Receta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecetaResponseMapper {
    @Mapping(target = "nombreChef", source = "chef.fullName")
    @Mapping(target = "tipoChef", source = "chef.tipoChef")
    @Mapping(target = "temporada", source = "receta.temporada")
    ResponseRecetaDTO toDTO(Receta receta, Chef chef);
}
