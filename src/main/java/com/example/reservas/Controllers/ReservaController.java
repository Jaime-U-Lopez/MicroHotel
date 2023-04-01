package com.example.reservas.Controllers;


import com.example.reservas.ClasesDto.ReservaDto;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import com.example.reservas.Service.ReservaService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Operation(summary = "Listar Reservas, puede ser todas o por fecha o por  id cliente ")
    public Object reservas(
            @RequestParam(required = false) Integer clienteReserva,
            @RequestParam(required = false) String fecha
    ) {
        if (clienteReserva != null) {
            return this.reservaService.ClientesConReserva(clienteReserva);
        } else if (fecha != null) {
            Date fechault = Date.valueOf(fecha);
            return this.reservaService.findByDateDisponibilidad(fechault);
        } else {
            return this.reservaService.reservaAll();
        }
    }




    @GetMapping("reservas/{idReserva}")
    @Operation(summary = "Listar Reserva por id ")
    public Reserva reserva(@PathVariable Integer idReserva) {
        return this.reservaService.reserva(idReserva);
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
    public boolean delete(@PathVariable Integer idReserva) {
        return this.reservaService.delete(idReserva);
    }





    @GetMapping("reservas/{fechaReserva}/")
    @Operation(summary = "Buscar Habitacion para reservar por fecha y tipo habitacion ")
    public List<Habitacion> findbyDateType(@PathVariable String fechaReserva, @RequestParam("tipoHabitacion") String tipoHabitacion) {
        Date fechault = Date.valueOf(fechaReserva);
        return this.reservaService.FindbyDateTypeRoom(fechault, tipoHabitacion);
    }

}
