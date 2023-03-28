package com.example.reservas.Service;

import com.example.reservas.Model.Habitacion;

import java.util.List;

public interface HabitacionServiceMetodos {

    Habitacion habitacion(int idHabitacion);
    List<Habitacion>  createArray(Habitacion[] habitacion);
    Habitacion  create(Habitacion habitacion);
    boolean delete(int idHabitacion);
    List<Habitacion> habitacionAll();

    List<Habitacion> habitacionPorTipo(String Tipo);

}
