package com.example.reservas.Repository;

import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;

import java.util.Optional;

public interface ConfirmacionReservaDao {

    Optional<String> ConfirmacionReserva(Reserva reserva);

    Double CalculoAPagar(Double valorHabitacion, String tipoHabitacion);
}
