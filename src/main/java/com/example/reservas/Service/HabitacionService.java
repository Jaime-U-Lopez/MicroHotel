package com.example.reservas.Service;

import com.example.reservas.Exception.HabitacionValidoException;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Repository.HabitacionImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HabitacionService implements HabitacionServiceMetodos {

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
    public List<Habitacion> createArray(Habitacion[] habitacion) {

        try {

            return this.habitacionImple.createsArray(habitacion);
        } catch (Exception e) {

            throw new HabitacionValidoException("No se ha creado las habitaciones valide el array ingredado ");
        }

    }

    @Override
    public Habitacion create(Habitacion habitacion) {


        try {
            return this.habitacionImple.create(habitacion);
        } catch (Exception e) {

            throw new HabitacionValidoException("El Habitacion no se pudo eliminar valide el id ingresado   ");
        }

    }

    @Override
    public boolean delete(int idHabitacion) {

        try {
            return this.habitacionImple.delete(idHabitacion);
        } catch (Exception e) {

            throw new HabitacionValidoException("El Habitacion no se pudo eliminar valide el id ingresado   ");
        }
    }

    @Override
    public List<Habitacion> habitacionAll() {


        try {
            return this.habitacionImple.habitacionAll();
        } catch (Exception e) {

            throw new HabitacionValidoException("El cliente no se pudo eliminar valide el id ingresado   ");
        }

    }

    @Override
    public List<Habitacion> habitacionPorTipo(String Tipo) {
        return this.habitacionImple.habitacionPorTipo(Tipo);
    }
}
