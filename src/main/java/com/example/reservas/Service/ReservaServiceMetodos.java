package com.example.reservas.Service;

import com.example.reservas.Model.Reserva;

import java.util.List;

public interface ReservaServiceMetodos {


    Reserva create(Reserva reserva);
    boolean delete(Integer idReserva);
    List<Reserva> reservaAll();
    Reserva reserva(Integer idReserva);




}
