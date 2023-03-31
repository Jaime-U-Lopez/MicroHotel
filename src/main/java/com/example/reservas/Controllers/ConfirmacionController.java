package com.example.reservas.Controllers;
import com.example.reservas.Model.Reserva;
import com.example.reservas.Service.ConfirmacionReservaService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
//@Api(tags = "ConfirmacionesReservas API", description = "Operaciones para gestionar Confirmaciones de reservas")
public class ConfirmacionController {

@Autowired
    private ConfirmacionReservaService confirmacionReservaService;


    @ApiResponses(value = {
            @ApiResponse(description = "400 - Bad Request", responseCode = "400"),
            @ApiResponse(description = "200 - Request successful", responseCode = "200"),
            @ApiResponse(description = "401 - Unauthorized", responseCode = "401"),
            @ApiResponse(description = "403 - Access Denied", responseCode = "403"),
            @ApiResponse(description = "404 - Not Found", responseCode = "404"),
            @ApiResponse(description = "500 - Internal error, please validate the entered fields", responseCode = "500")
    })
  @Operation(summary = "Crear una confirmacion de reserva, para el tipo habitación  premiun tiene un descuento del 5% ")
  @PostMapping("confirmacion")
    public Optional<String> confirmacionReserva(@RequestBody Reserva reserva) {
        return this.confirmacionReservaService.ConfirmacionReserva(reserva);
    }
}
