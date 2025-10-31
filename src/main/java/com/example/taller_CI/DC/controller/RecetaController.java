package com.example.taller_CI.DC.controller;

import com.example.taller_CI.DC.model.DTO.Request.RequestRecetaDTO;
import com.example.taller_CI.DC.model.DTO.Response.ResponseRecetaDTO;
import com.example.taller_CI.DC.service.Implementaciones.RecetaServiceImp;
import com.example.taller_CI.DC.service.Interfaces.RecetaService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recetas")
public class RecetaController {
    private final RecetaService recetaServiceImp;

    @Operation(
            summary = "registrar una nueva receta",
            description = "permite crear una receta ingresada por un jurado, televidente o concursante."
    )
    @ApiResponse(responseCode = "201", description = "Receta creada ",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseRecetaDTO.class)))
    @PostMapping
    public ResponseEntity<ResponseRecetaDTO> crearReceta(@Valid @RequestBody RequestRecetaDTO recetaDTO) {
        return ResponseEntity.ok(recetaServiceImp.crearReceta(recetaDTO));
    }


    @Operation(summary = "Obtener todas las recetas registradas")
    @ApiResponse(responseCode = "200", description = "lista de recetas devuelto correctamente")
    @GetMapping
    public ResponseEntity<List<ResponseRecetaDTO>> obtenerRecetas() {
        return ResponseEntity.ok(recetaServiceImp.obtenerRecetas());
    }


    @Operation(summary = "Obtener una receta por su ID")
    @ApiResponse(responseCode = "200", description = "Receta encontrada")
    @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseRecetaDTO> obtenerRecetaPorId(@PathVariable String id) {
        return ResponseEntity.ok(recetaServiceImp.obtenerRecetaPorId(id));
    }


    @Operation(summary = "Buscar recetas por tipo de chef (TELEVIDENTE, PARTICIPANTE o CHEF)")
    @GetMapping("/tipo-chef/{chef}")
    public ResponseEntity<List<ResponseRecetaDTO>> obtenerRecetasPorChef(@PathVariable String chef) {
        return ResponseEntity.ok(recetaServiceImp.obtenerRecetasPorChef(chef));
    }


    @Operation(summary = "Buscar recetas por temporada ")
    @GetMapping("/temporada/{temporada}")
    public  ResponseEntity<List<ResponseRecetaDTO>> obtenerRecetasPorTemporada(@PathVariable int temporada) {
        return ResponseEntity.ok(recetaServiceImp.obtenerRecetasPorTemporada(temporada));
    }

    @Operation(summary = "Buscar recetas que incluyan un ingrediente en especifico")
    @GetMapping("/ingrediente/{ingrediente}")
    public ResponseEntity<List<ResponseRecetaDTO>> obtenerRecetasPorIngrediente(@PathVariable String ingrediente) {
        return ResponseEntity.ok(recetaServiceImp.obtenerRecetasPorIngrediente(ingrediente));
    }


    @Operation(summary = "Actualizar una receta")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseRecetaDTO> actualizarReceta(@PathVariable String id,@Valid  @RequestBody RequestRecetaDTO recetaDTO) {
        return ResponseEntity.ok(recetaServiceImp.actualizarRecetaPorId(id, recetaDTO));
    }

    @Operation(summary = "Eliminar una receta por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable String id) {
        recetaServiceImp.eliminarRecetaPorId(id);
        return ResponseEntity.ok().build();
    }
}
