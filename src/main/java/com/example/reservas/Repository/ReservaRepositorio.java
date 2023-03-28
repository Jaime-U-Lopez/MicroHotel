package com.example.reservas.Repository;

import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservaRepositorio extends JpaRepository<Reserva,Long> {

    @Query("SELECT j,f FROM Habitacion f , Reserva j WHERE f.tipoHabitacion = :tipo and j.fecha_Reserva =:date ")
    List<Reserva> findByTipoFechaYHabitacion(@Param("tipo") String tipo, @Param("date")  String date);


    //Reserva findByCodigoReserva(String codigoReserva);

    //List<Reserva> findByCliente(Cliente cliente);

}
