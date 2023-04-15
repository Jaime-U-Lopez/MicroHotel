package com.example.reservas.Controllers;


import com.example.reservas.Dto.ReservaDto;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import com.example.reservas.Service.ReservaService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
@Api(tags = "Reservas de Hotel ", description = "Operaciones para gestionar las Reservas del Hotel ")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("reservas")
    @Operation(summary = "En esta opcion podras rellenar los campos a necesidad para las " +
            "siguiente consultas : - Listar todas Reservas (no diligencias campos)" +
            " - Id cliente con reserva ( solo el campo de id cliente ) " +
            " - Consulta por fecha y por  id cliente las" +
            " - Consulta por fecha las habitaciones disponibles " +
            " - Id reserva "
    )

    public Object reservas(
            @RequestParam(required = false) @ApiParam(value = "Id cliente con Reserva ", example = "123") Integer clienteReserva,
            @RequestParam(required = false) @ApiParam(value = "Ingresa la fecha a consultar  ", example = "2000-05.22") String fecha,
            @RequestParam(required = false) @ApiParam(value = "Id de  Reserva a consultar ", example = "2123") Integer idReserva
    ) {
        if (clienteReserva != null) {
            return this.reservaService.ClientesConReserva(clienteReserva);
        } else if (fecha != null) {
            Date fechault = Date.valueOf(fecha);
            return this.reservaService.findByDateRoomDisponibilidad(fechault);
        }else if (idReserva != null) {
            return this.reservaService.reserva(idReserva);

        }
        else {
            return this.reservaService.reservaAll();
        }
    }



    @GetMapping("reservas/{fechaReserva}/")
    @Operation(summary = "Buscar Habitacion para reservar por fecha y tipo habitacion ")
    public List<Habitacion> findbyDateTypeRoom(@PathVariable String fechaReserva, @RequestParam("tipoHabitacion") String tipoHabitacion) {
        Date fechault = Date.valueOf(fechaReserva);
        return this.reservaService.FindbyDateByTypeRoom(fechault, tipoHabitacion);
    }


    @PostMapping("reservas")
    @Operation(summary = "Crear Reserva en la base de datos ")
    public ResponseEntity<ReservaDto> createReserva(@RequestBody ReservaDto reservaDto) {
        Optional<ReservaDto> validacionReserva = Optional.of(reservaService.create(reservaDto));
        if (validacionReserva != null) {
            return new ResponseEntity(validacionReserva.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity("Reserva not created!", HttpStatus.BAD_REQUEST);

    }




    @DeleteMapping("reservas/{idReserva}")
    @Operation(summary = "Elimina Reserva en la base de datos ")
    public ResponseEntity<?> delete(@PathVariable Integer idReserva) {


         this.reservaService.delete(idReserva);

         String message =" La reserva "+idReserva+ " se elimino con exito";
    return ResponseEntity.ok(message);
    }





}
