package com.example.taller_CI.DC.ApiTest;


import com.example.taller_CI.DC.Repository.ChefRepository;
import com.example.taller_CI.DC.Repository.RecetaRepository;
import com.example.taller_CI.DC.Util.Exceptions.RecetaNotFoundException;
import com.example.taller_CI.DC.Util.Request.RecetaRequestMapper;
import com.example.taller_CI.DC.Util.Response.RecetaResponseMapper;
import com.example.taller_CI.DC.model.Chef;
import com.example.taller_CI.DC.model.DTO.Request.RequestRecetaDTO;
import com.example.taller_CI.DC.model.DTO.Response.ResponseRecetaDTO;
import com.example.taller_CI.DC.model.Receta;
import com.example.taller_CI.DC.model.tipoChef;
import com.example.taller_CI.DC.service.Implementaciones.RecetaServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class RecetaServiceTest {

    @Mock
    private RecetaRepository recetaRepository;

    @Mock
    private ChefRepository chefRepository;

    @Mock
    private RecetaRequestMapper recetaRequestMapper;

    @Mock
    private RecetaResponseMapper recetaResponseMapper;


    @InjectMocks
    private RecetaServiceImp recetaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void regristrarReceta() {
        RequestRecetaDTO dto = new RequestRecetaDTO();
        dto.setTitulo("Tarta de Chocolate");
        dto.setIngredientes(List.of("Harina", "Cacao", "Azúcar"));
        dto.setPasosPreparacion(List.of("Mezclar, hornear 40 minutos"));
        dto.setChefId("123");
        dto.setTemporada(3);


        Receta receta = new Receta();
        receta.setTitulo(dto.getTitulo());
        receta.setFecha(LocalDate.now());
        receta.setIngredientes(dto.getIngredientes());
        receta.setPasosPreparacion(dto.getPasosPreparacion());
        receta.setChefId(dto.getChefId());
        receta.setTemporada(dto.getTemporada());


        Chef chef = new Chef();
        chef.setChefId("123");
        chef.setFullName("Gordon");
        chef.setTipoChef(tipoChef.JURADO);

        ResponseRecetaDTO dtoResponse = new ResponseRecetaDTO();

        dtoResponse.setTipoChef(chef.getTipoChef().toString());
        dtoResponse.setPasosPreparacion(receta.getPasosPreparacion());
        dtoResponse.setFecha(receta.getFecha());
        dtoResponse.setTitulo(receta.getTitulo());
        dtoResponse.setIngredientes(receta.getIngredientes());
        dtoResponse.setTemporada(receta.getTemporada());
        dtoResponse.setNombreChef(chef.getFullName());

        when(chefRepository.findById("123")).thenReturn(Optional.of(chef));

        when(recetaRequestMapper.toEntity(dto)).thenReturn(receta);

        when(recetaResponseMapper.toDTO(receta,chef)).thenReturn(dtoResponse);

        when(recetaRepository.save(any(Receta.class))).thenReturn(receta);



        ResponseRecetaDTO response = recetaService.crearReceta(dto);

        verify(recetaRepository, times(1)).save(any(Receta.class));
        verify(recetaRequestMapper).toEntity(dto);
        verify(recetaRepository).save(receta);
        verify(recetaResponseMapper).toDTO(receta,chef);
         assertNotNull(response);
         assertEquals("Tarta de Chocolate", response.getTitulo());
    }


    @Test
    void validarIngredienteEnBusqueda(){
        Receta receta = new Receta(
                "1",
                "Tarta de Chocolate",
                List.of("Harina", "Cacao", "Azucar"),
                List.of("Mezclar, hornear 40 minutos"),
                "123",
                3,
                LocalDate.now()
        );

        List<Receta> listaReceta = List.of(receta);

        Chef chef = new Chef(
                "123",
                tipoChef.JURADO,
                3,
                "Gordon"
        );

        Map<String,Chef> mapChef = new HashMap<>();
        mapChef.put("123",chef);

        List<Chef> chefs = List.of(chef);

        ResponseRecetaDTO dtoResponse = new ResponseRecetaDTO();
        dtoResponse.setTipoChef(tipoChef.JURADO.toString());
        dtoResponse.setPasosPreparacion(List.of("Mezclar, hornear 40 minutos"));
        dtoResponse.setFecha(receta.getFecha());
        dtoResponse.setTitulo("Tarta de Chocolate");
        dtoResponse.setIngredientes(List.of("Harina", "Cacao", "Azucar"));
        dtoResponse.setTemporada(3);
        dtoResponse.setNombreChef("Gordon");




        when(chefRepository.findAll()).thenReturn(chefs);
        when(recetaRepository.findByIngredientesContaining("Cacao")).thenReturn(listaReceta);
        when(recetaResponseMapper.toDTO(receta,chef)).thenReturn(dtoResponse);
        when(recetaRepository.findByIngredientesContaining("Pimienta")).thenReturn(Collections.emptyList());


        List<ResponseRecetaDTO> dtoListasIngre2 = recetaService.obtenerRecetasPorIngrediente("Pimienta");
        List<ResponseRecetaDTO> dtoListasIngre = recetaService.obtenerRecetasPorIngrediente("Cacao");

        verify(recetaRepository, times(1)).findByIngredientesContaining("Cacao");
        assertNotNull(dtoListasIngre);
        assertTrue(dtoListasIngre2.isEmpty());
        assertEquals(dtoListasIngre.get(0).getTitulo(),receta.getTitulo());

    }



    @Test
    void verificarErrorConsultaInexistente(){

        when(recetaRepository.findById(anyString())).thenThrow(new RecetaNotFoundException("Receta no encontrada"));

        RecetaNotFoundException recetaExc = assertThrows(RecetaNotFoundException.class, () -> {
            recetaService.obtenerRecetaPorId("123");
        });



        assertEquals("Receta no encontrada", recetaExc.getMessage());

    }

}
