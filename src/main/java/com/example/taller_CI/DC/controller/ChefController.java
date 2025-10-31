package com.example.taller_CI.DC.controller;


import com.example.taller_CI.DC.model.DTO.Request.RequestChefDTO;
import com.example.taller_CI.DC.model.DTO.Response.ResponseChefDTO;
import com.example.taller_CI.DC.service.Interfaces.ChefService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chefs")
public class ChefController {
    private final ChefService chefService;


    @Operation(summary = "Añadir un nuevo chef ")
    @ApiResponse(responseCode = "201", description = "Chef creado")
    @PostMapping
    public ResponseEntity<ResponseChefDTO> crearChef(@RequestBody RequestChefDTO dto) {
        ResponseChefDTO response = chefService.crearChef(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Actualizar un chef ")
    @PutMapping("/{chefId}")
    public ResponseEntity<ResponseChefDTO> editarChef(@PathVariable String chefId,@RequestBody RequestChefDTO dto) {
        ResponseChefDTO response = chefService.editarChef(dto, chefId);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Eliminar un chef por su id")
    @DeleteMapping("/{chefId}")
    public ResponseEntity<Void> eliminarChef(@PathVariable String chefId) {
        chefService.eliminarChef(chefId);
        return ResponseEntity.noContent().build();
    }

}
