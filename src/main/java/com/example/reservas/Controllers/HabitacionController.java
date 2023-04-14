package com.example.reservas.Controllers;

import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Service.HabitacionService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
@Api(tags = "Habitaciones Hotel ", description = "Operaciones para gestionar las Habitaciones del Hotel ")
public class HabitacionController {


    private HabitacionService habitacionService;
    @Autowired
    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }



    @ApiResponses(value = {
            @ApiResponse(description = "400 - Bad Request", responseCode = "400"),
            @ApiResponse(description = "200 - Request successful", responseCode = "200"),
            @ApiResponse(description = "401 - Unauthorized", responseCode = "401"),
            @ApiResponse(description = "403 - Access Denied", responseCode = "403"),
            @ApiResponse(description = "404 - Not Found", responseCode = "404"),
            @ApiResponse(description = "500 - Internal error, please validate the entered fields", responseCode = "500")
    })
    @GetMapping("habitaciones")
    @Operation(summary = "Lista de todas las habitaciones ")
    public List<Habitacion> habitacionAll(){
        return this.habitacionService.habitacionAll();
    }


   @GetMapping("habitaciones/{idHabitacion}")
   @Operation(summary = "Traer habitacion ṕor Id ")
   public ResponseEntity<?>  habitacion (@PathVariable @ApiParam(value = "ID de la  habitación  a consultar  ", example = "123") int idHabitacion){

       Optional<Habitacion> habitacion = Optional.ofNullable(this.habitacionService.habitacion(idHabitacion));
       return ResponseEntity.status(HttpStatus.CREATED).body(habitacion.get());



}
    @GetMapping("habitaciones/")
    @Operation(summary = "Traer habitacion por tipo estandar o premiun ")
    public List<Habitacion> habitacionPorTipo(@RequestParam("tipo") @ApiParam(value = "Ingresa el tipo a consultar Premiun o Standar", example = "Standar") String tipo){
        return  this.habitacionService.habitacionPorTipo(tipo);
    }


    @PostMapping("habitaciones")
    @Operation(summary = "Crear una habitacion en la base de datos ")
    public Habitacion createHabitacion(@RequestBody Habitacion habitacion){
        return this.habitacionService.create(habitacion);
    }

    @DeleteMapping("habitaciones/{idHabitacion}")
    @Operation(summary = "Eliminar una habitacion de la base de datos ")
    public ResponseEntity<?> delete(@PathVariable @ApiParam(value = "ID del habitacion  a eliminar ", example = "123") int idHabitacion){


        Boolean confirmacion= this.habitacionService.delete(idHabitacion);
        if(confirmacion){
            String mensage= "El cliente con id : " + idHabitacion + " se elimino con Exito!";
            return ResponseEntity.ok(mensage);

        } else{
            String mensage = "No se puede eliminar el id "+ idHabitacion;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensage);

        }

    }


}
