package com.example.reservas.Service;

import com.example.reservas.Dto.ReservaDto;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;

import java.sql.Date;
import java.util.List;

public interface ReservaServiceMetodos {


    ReservaDto create(ReservaDto reserva);
    boolean delete(Integer idReserva);
    List<Reserva> reservaAll();
    Reserva reserva(Integer idReserva);

   List<Habitacion> FindbyDateByTypeRoom(Date fecha, String tipoHabitacion);
    List<Habitacion> findByDateRoomDisponibilidad(Date fecha);
    public List<Cliente> ClientesConReserva(Integer cedula);

}
