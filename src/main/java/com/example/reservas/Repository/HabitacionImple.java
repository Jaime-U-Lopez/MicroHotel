package com.example.reservas.Repository;

import com.example.reservas.Model.Habitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class HabitacionImple implements HabitacionDao {

    private HabitacionRepositorio habitacionRepositorio;

    @Autowired
    public HabitacionImple(HabitacionRepositorio habitacionRepositorio) {
        this.habitacionRepositorio = habitacionRepositorio;
    }
    @Override
    public Habitacion habitacion(int idHabitacion) {

        try {
            Optional<Habitacion> habitacion = this.habitacionRepositorio.findById(idHabitacion);
            return habitacion.get();
        } catch (Exception ex) {
            throw new RuntimeException("la persona no existe en la base de datos o no se esta conectando a la base de datos  ");
        }
    }

    @Override
    public List<Habitacion> habitacionPorTipo(String tipoHabitacion) {
        return this.habitacionRepositorio.findByTipoHabitacion(tipoHabitacion);
    }

    @Override
    public List<Habitacion> createsArray(Habitacion[] habitacion) {
        List<Habitacion>  habitaciones= new ArrayList<>();

        for (Habitacion item  : habitacion) {
            habitaciones.add(item);
        }
        return habitaciones;
    }

    @Override
    public List<Habitacion> habitacionAll() {
        List<Habitacion> habitacions = new ArrayList<>();
        this.habitacionRepositorio.findAll().forEach(item -> habitacions.add(item));
        return habitacions;
    }



    @Override
    public Habitacion create(Habitacion habitacion) {

        return this.habitacionRepositorio.save(habitacion);
    }

    @Override
    public boolean delete(int idHabitacion) {
        boolean existe = this.habitacionRepositorio.existsById(idHabitacion);
        if (existe) {
            this.habitacionRepositorio.deleteById(idHabitacion);
            return true;
        }

        return false;
    }


}
