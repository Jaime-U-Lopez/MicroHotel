package com.example.reservas.Repository;

import com.example.reservas.Model.Reserva;

import java.util.List;

public interface ReservasDao {

    Reserva create(Reserva reserva);
    boolean delete(Long idReserva);
    List<Reserva> reservaAll();
    Reserva reserva(Long idReserva);

    List<Reserva>  QueryConsultaPorFechaYHabitacion( String tipoHabitacion, String fecha );


}
