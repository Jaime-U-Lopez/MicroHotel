package com.example.reservas.Service;


import com.example.reservas.Model.Reserva;
import com.example.reservas.Repository.ConfirmacionReservaImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmacionReservaService {

    private ConfirmacionReservaImple confirmacionReservaImple;


    @Autowired

    public ConfirmacionReservaService(ConfirmacionReservaImple confirmacionReservaImple) {
        this.confirmacionReservaImple = confirmacionReservaImple;
    }


    public String ConfirmacionReserva(Reserva reserva){


            if(reserva.equals(null)){

                throw new RuntimeException("La reserva tiene campos vacios");
            }


        return  this.confirmacionReservaImple.ConfirmacionReserva(reserva);

    }


}
