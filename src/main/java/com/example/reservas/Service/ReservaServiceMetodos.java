package com.example.reservas.Service;

import com.example.reservas.Model.Reserva;

import java.util.List;

public interface ReservaServiceMetodos {


    String create(Reserva reserva);
    boolean delete(Long idReserva);
    List<Reserva> reservaAll();
    Reserva reserva(Long idReserva);
    List<Reserva>  queryConsultaPorFechaYHabitacion( String tipoHabitacion, String fecha );

    Double totalApagar(int valorBaseHabitacion);

}
