package com.example.reservas.Repository;

import com.example.reservas.Model.Habitacion;

import java.util.List;

public interface HabitacionDao {



    Habitacion habitacion(int idHabitacion);
    List<Habitacion> habitacionPorTipo(String tipoHabitacion);

    List<Habitacion> createsArray(Habitacion[] habitacion);

    Habitacion create(Habitacion habitacion);
    boolean delete(int idHabitacion);
    List<Habitacion> habitacionAll();




}
