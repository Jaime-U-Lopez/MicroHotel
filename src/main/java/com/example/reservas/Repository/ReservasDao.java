package com.example.reservas.Repository;

import com.example.reservas.Dto.ReservaDto;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;

import java.sql.Date;
import java.util.List;

public interface ReservasDao {

    Reserva create(ReservaDto reservaDto);
    boolean delete(Integer idReserva);
    List<Reserva> reservaAll();
    Reserva reserva(Integer idReserva);

    public List<Habitacion> FindbyDateTypeRoom(Date fecha , String TipoHabitacion);

    public List<Habitacion> findByDateDisponibilidad(Date fecha);

    public List<Cliente> ClientesConReserva(Integer cedula);
}
