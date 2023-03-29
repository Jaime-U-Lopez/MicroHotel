package com.example.reservas.Repository;

import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;

public interface ConfirmacionReservaDao {

    String ConfirmacionReserva(Reserva reserva);

    Double CalculoAPagar(Double valorHabitacion, String tipoHabitacion);
}
