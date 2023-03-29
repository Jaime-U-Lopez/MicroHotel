package com.example.reservas.Repository;


import com.example.reservas.Model.Reserva;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class ConfirmacionReservaImple implements ConfirmacionReservaDao {
    @Override
    public String ConfirmacionReserva(Reserva reserva) {

        Integer numeroReserva = reserva.getCodigo_reserva();
        Integer numeroHabitacion = reserva.getHabitacion().getNumero_habitacion();
        Date fechaReserva = reserva.getFecha_Reserva();
        String tipoHabitacion=reserva.getHabitacion().getTipoHabitacion();
        Double valorHabitacion=reserva.getHabitacion().getPrecio();
        Double totalApagar= CalculoAPagar(valorHabitacion,tipoHabitacion );
        return "{Su reserva esta ok, numero de reserva +" + numeroReserva+
                "Habitacion "+ numeroHabitacion+
                "Fecha reserva" + fechaReserva+
                "Total a pagar" + totalApagar+
                " }";
    }

    @Override
    public Double CalculoAPagar(Double valorHabitacion, String tipoHabitacion) {


        Double descuento = 0.0;
        Double totalPago = 0.0;


        if (valorHabitacion.equals(null) || tipoHabitacion.equals(null)) {
            throw new RuntimeException("El valor o el tipo habitacion estan vacios o no corresponden al formato Double y String");

        }
        if (tipoHabitacion.equals("premiun")) {
            descuento = valorHabitacion * 0.05;
            totalPago = valorHabitacion - descuento;
        } else {
            totalPago = valorHabitacion;
        }
        return totalPago;

    }


}
