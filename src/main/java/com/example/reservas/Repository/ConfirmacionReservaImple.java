package com.example.reservas.Repository;


import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public class ConfirmacionReservaImple implements ConfirmacionReservaDao {
    @Override
    public Optional<String> ConfirmacionReserva(Reserva reserva) {

        Integer numeroReserva = reserva.getCodigoReserva();
        Integer numeroHabitacion = reserva.getHabitacion().getNumero_habitacion();
        LocalDate fechaReserva = Optional.ofNullable(reserva.getFechaReserva())
                .map(Date::toLocalDate)
                .orElse(null);
        String tipoHabitacion = Optional.ofNullable(reserva.getHabitacion())
                .map(Habitacion::getTipoHabitacion)
                .orElse(null);
        Double valorHabitacion = Optional.ofNullable(reserva.getHabitacion())
                .map(Habitacion::getPrecio)
                .orElse(null);

        if (numeroReserva == null || numeroHabitacion == null || fechaReserva == null ||
                tipoHabitacion == null || valorHabitacion == null) {
            return Optional.empty();
        }

        Double totalAPagar = CalculoAPagar(valorHabitacion, tipoHabitacion);

        return Optional.of("Su reserva está OK. Número de reserva: " + numeroReserva +
                ", Habitación: " + numeroHabitacion +
                ", Fecha reserva: " + fechaReserva +
                ", Total a pagar: " + totalAPagar);
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
