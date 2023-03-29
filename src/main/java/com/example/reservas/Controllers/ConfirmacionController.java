package com.example.reservas.Controllers;
import com.example.reservas.Model.Reserva;
import com.example.reservas.Service.ConfirmacionReservaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class ConfirmacionController {


    private ConfirmacionReservaService confirmacionReservaService;


    @PostMapping("confirmacion")
    public String confirmacionReserva(@RequestBody Reserva reserva) {

        return this.confirmacionReservaService.ConfirmacionReserva(reserva);

    }
}
