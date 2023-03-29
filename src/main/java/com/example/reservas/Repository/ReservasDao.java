package com.example.reservas.Repository;

import com.example.reservas.Model.Reserva;

import java.util.List;

public interface ReservasDao {

    Reserva create(Reserva reserva);
    boolean delete(Integer idReserva);
    List<Reserva> reservaAll();
    Reserva reserva(Integer idReserva);





}
