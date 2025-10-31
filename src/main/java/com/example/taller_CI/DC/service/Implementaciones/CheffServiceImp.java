package com.example.taller_CI.DC.service.Implementaciones;


import com.example.taller_CI.DC.Repository.ChefRepository;
import com.example.taller_CI.DC.Util.Exceptions.ChefNotFoundException;
import com.example.taller_CI.DC.Util.Request.ChefRequestMapper;
import com.example.taller_CI.DC.Util.Response.ChefResponseMapper;
import com.example.taller_CI.DC.model.Chef;
import com.example.taller_CI.DC.model.DTO.Request.RequestChefDTO;
import com.example.taller_CI.DC.model.DTO.Response.ResponseChefDTO;
import com.example.taller_CI.DC.model.tipoChef;
import com.example.taller_CI.DC.service.Interfaces.ChefService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheffServiceImp implements ChefService {
    private final ChefRepository chefRepository;
    private final ChefRequestMapper requestChefMapper;
    private final ChefResponseMapper responseChefMapper;


    public ResponseChefDTO crearChef(RequestChefDTO dto) {
        if(dto.getTipoChef() == tipoChef.CONCURSANTE){
            dto.setTemporada(null);
        }
            Chef chef = requestChefMapper.toEntity(dto);
        return responseChefMapper.toDTO(chefRepository.save(chef));
    }

    public ResponseChefDTO editarChef(RequestChefDTO dto, String chefId) {
        Chef chef = chefRepository.findById(chefId).orElseThrow(() -> new ChefNotFoundException("Chef no encontrado"));
        chef.setTipoChef(dto.getTipoChef());
        chef.setTemporada(chef.getTemporada());
        chef.setFullName(dto.getFullName());
        return responseChefMapper.toDTO(chefRepository.save(chef));
    }

    public void eliminarChef(String chefId) {
        chefRepository.deleteById(chefId);
    }

}
