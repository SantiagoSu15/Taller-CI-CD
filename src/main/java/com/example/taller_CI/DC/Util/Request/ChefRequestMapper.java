package com.example.taller_CI.DC.Util.Request;

import com.example.taller_CI.DC.model.Chef;
import com.example.taller_CI.DC.model.DTO.Request.RequestChefDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChefRequestMapper {
    Chef toEntity(RequestChefDTO dto);
}
