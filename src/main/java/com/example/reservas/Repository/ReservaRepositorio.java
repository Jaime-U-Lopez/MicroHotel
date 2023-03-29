package com.example.reservas.Repository;

import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ReservaRepositorio extends JpaRepository<Reserva,Integer> {



  @Query("SELECT r FROM Reserva r, Habitacion h   where r.fecha_Reserva =?1  and h.tipoHabitacion=?2")
  public List<Habitacion> findByDateType(Date fechaReserva, String tipoHabitacion);

  @Query("SELECT c,r FROM Reserva r, Cliente  c   where r.cedula =?1 ")
  public List<Cliente> clienteConReserva(Integer cedula);


@Query("Select h FROM Habitacion h WHERE NOT EXISTS (SELECT r FROM Reserva r where r.fecha_Reserva=:fechaReserva and r.habitacion=h)")
public List<Habitacion> findByDateDisponibilidad(@Param("fechaReserva") Date fechaReserva);

}
