package com.example.reservas.Controllers;


import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import com.example.reservas.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("reservas")
    public List<Reserva> reservasAll() {
        return this.reservaService.reservaAll();
    }

    @GetMapping("reservas/{idReserva}")
    public Reserva reserva(@PathVariable Integer idReserva) {


        return this.reservaService.reserva(idReserva);
    }


    @PostMapping("reservas")
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {

        Optional<Reserva> validacionReserva = Optional.of(reservaService.create(reserva));
        if (validacionReserva != null) {
            return new ResponseEntity(validacionReserva.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity("Reserva not created!", HttpStatus.BAD_REQUEST);

    }


    @GetMapping("reservas/")
    public List<Cliente> ClientesConReserva(@RequestParam ("clienteReserva") Integer  clienteReserva ){
        return this.reservaService.ClientesConReserva(clienteReserva);

    }


    @DeleteMapping("reservas/{idReserva}")
    public boolean delete(@PathVariable Integer idReserva) {


        return this.reservaService.delete(idReserva);
    }


    @GetMapping("reservas/")
    public List<Habitacion> ConsultaPorFechaDisponibilidad(@RequestParam("fecha") String fecha) {
        Date fechault = Date.valueOf(fecha);
        return this.reservaService.findByDateDisponibilidad(fechault);
    }


    //por fecha
    @GetMapping("reservas/{fechaReserva}/")
    public List<Habitacion> findbyDateType(@PathVariable String fechaReserva, @RequestParam("tipoHabitacion") String tipoHabitacion) {
        Date fechault = Date.valueOf(fechaReserva);
        return this.reservaService.FindbyDateTypeRoom(fechault, tipoHabitacion);
    }

}
