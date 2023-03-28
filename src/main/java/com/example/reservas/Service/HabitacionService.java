package com.example.reservas.Service;

import com.example.reservas.Model.Habitacion;
import com.example.reservas.Repository.HabitacionImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HabitacionService  implements HabitacionServiceMetodos{

    private HabitacionImple habitacionImple;

@Autowired
    public HabitacionService(HabitacionImple habitacionImple) {
        this.habitacionImple = habitacionImple;
    }

    @Override
    public Habitacion habitacion(int idHabitacion) {
        return this.habitacionImple.habitacion(idHabitacion);
    }

    @Override
    public List<Habitacion>  createArray(Habitacion [] habitacion) {
        return this.habitacionImple.createsArray(habitacion);
    }

    @Override
    public Habitacion create(Habitacion habitacion) {
        return this.habitacionImple.create(habitacion);
    }

    @Override
    public boolean delete(int idHabitacion) {
        return this.habitacionImple.delete(idHabitacion);
    }

    @Override
    public List<Habitacion> habitacionAll() {
        return this.habitacionImple.habitacionAll();
    }

    @Override
    public List<Habitacion> habitacionPorTipo(String Tipo) {
        return this.habitacionImple.habitacionPorTipo(Tipo);
    }
}
