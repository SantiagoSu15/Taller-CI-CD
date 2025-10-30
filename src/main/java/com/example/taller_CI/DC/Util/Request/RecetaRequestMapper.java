package com.example.taller_CI.DC.Util.Request;

import com.example.taller_CI.DC.model.DTO.Request.RequestRecetaDTO;
import com.example.taller_CI.DC.model.Receta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecetaRequestMapper {
    Receta toEntity(RequestRecetaDTO dto);
}
