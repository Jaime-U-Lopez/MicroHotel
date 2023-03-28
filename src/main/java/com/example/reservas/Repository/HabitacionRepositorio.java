package com.example.reservas.Repository;

import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Tipohabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface HabitacionRepositorio  extends JpaRepository<Habitacion,Integer> {
    @Query("SELECT f FROM Habitacion f WHERE f.tipoHabitacion = :tipo")
     List<Habitacion> findByTipoHabitacion(@Param("tipo") String tipo);


    //List<Habitacion> findByReservasFechaReserva(Date fecha);

  //  List<Habitacion> findByTipoAndReservasFechaReserva(TipoHabitacion tipo, Date fecha);

}
