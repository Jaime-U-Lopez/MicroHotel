package com.example.reservas.Controllers;


import com.example.reservas.Model.Reserva;
import com.example.reservas.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("reservas")
    public List<Reserva> reservasAll(){
        return this.reservaService.reservaAll();
    }

    @GetMapping("reservas/{idReserva}")
    public Reserva reserva(@PathVariable Long idReserva){
        return  this.reservaService.reserva(idReserva);
    }
    @PostMapping("reservas")
    public ResponseEntity<Reserva>  createReserva(@RequestBody Reserva reserva){

        Optional<String> validacionReserva = Optional.ofNullable(reservaService.create(reserva));
        if (validacionReserva != null) {
            return new ResponseEntity("Created Reserva!", HttpStatus.CREATED);

        }
        return new ResponseEntity("Reserva not created!", HttpStatus.BAD_REQUEST);




    }
    @DeleteMapping("reservas/{idReserva}")
    public boolean delete(@PathVariable Long idReserva){
        return this.reservaService.delete(idReserva);
    }

    @GetMapping("reservas/{tipo-habitacion}/{fecha}")
    public List<Reserva> QueryPorFechaYHabitacionTipo(@PathVariable String tipo_habitacion, @PathVariable String fecha){
        return this.reservaService.queryConsultaPorFechaYHabitacion(tipo_habitacion,  fecha);
    }

    @PutMapping("reservas")
    public void update(@RequestBody Reserva reserva){

    }









}
