package com.example.reservas.Service;

import com.example.reservas.Exception.ClienteInvalidoException;
import com.example.reservas.Exception.HabitacionValidoException;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Repository.HabitacionImple;
import com.example.reservas.Util.ValidationMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HabitacionService implements HabitacionServiceMetodos {

    private HabitacionImple habitacionImple;

    @Autowired
    public HabitacionService(HabitacionImple habitacionImple) {
        this.habitacionImple = habitacionImple;
    }

    @Override
    public Habitacion habitacion(int idHabitacion) throws RuntimeException {

        Optional<Habitacion> optionalHabitacion= Optional.ofNullable(this.habitacionImple.habitacion(idHabitacion));

        if(!optionalHabitacion.isPresent()){
            throw new HabitacionValidoException("La habitacion : "+idHabitacion+" no existe en la base de datos");

        }
        return optionalHabitacion.get();


    }




    @Override
    public List<Habitacion> createArray(Habitacion[] habitacion) throws RuntimeException {

        try {

            return this.habitacionImple.createsArray(habitacion);
        } catch (Exception e) {

            throw new HabitacionValidoException("No se ha creado las habitaciones valide el array ingredado ");
        }

    }

    @Override
    public Habitacion create(Habitacion habitacion)throws RuntimeException {

        Integer habitacionNumero=habitacion.getNumero_habitacion();
        String tipoHabitacion=habitacion.getTipoHabitacion();
        Double precio=habitacion.getPrecio();

        validarHabitacion(habitacionNumero,precio );

        Optional<Habitacion> habitacion1= Optional.ofNullable(this.habitacionImple.create(habitacion));
         if (tipoHabitacion=="Standar"&& habitacion1.isPresent() || tipoHabitacion=="Premiun" &&habitacion1.isPresent()) {
            return habitacion1.get();
        }else {
             throw new HabitacionValidoException("La Habitacion " +habitacion +"no es Standar o Premiun");

        }


    }

    private void validarHabitacion(Integer numeroHabitacion,  Double precio ) throws RuntimeException {
        if (numeroHabitacion <=0  || precio<=0) {
            throw new HabitacionValidoException("El numero de habitacion :"+ numeroHabitacion.toString() + " y precio: " + precio + " no puede ser negativo o cero : ");
        }
        Optional<Habitacion> optionalHabitacion= Optional.ofNullable(this.habitacionImple.habitacion(numeroHabitacion));
        if(optionalHabitacion.isPresent()){

            throw new HabitacionValidoException("La habitacion : "+ numeroHabitacion + " , ya  existe en la base de datos");
        }
    }


    @Override
    public boolean delete(int idHabitacion)throws RuntimeException {

        // con esto validamos que la habitacion exista antes de eliminar o sino genera una exception
        this.habitacion(idHabitacion);

       Optional<Boolean> validacionHabitacion= Optional.of(habitacionImple.delete(idHabitacion));
        if(!validacionHabitacion.isPresent()){
            throw new HabitacionValidoException("El Habitacion :"+idHabitacion  +"  no se pudo eliminar valide el id ingresado   ");
        }

        return true;
    }

    @Override
    public List<Habitacion> habitacionAll() throws RuntimeException {


        try {
            return this.habitacionImple.habitacionAll();
        } catch (Exception e) {

            throw new HabitacionValidoException("El cliente no se pudo eliminar valide el id ingresado   ");
        }

    }

    @Override
    public List<Habitacion> habitacionPorTipo(String Tipo) throws RuntimeException{

        Optional<List<Habitacion>> optionalHabitacion= Optional.ofNullable(this.habitacionImple.habitacionPorTipo(Tipo));
        if(Tipo.equalsIgnoreCase("Standar")  && optionalHabitacion.isPresent()||Tipo.equalsIgnoreCase("premiun") &&optionalHabitacion.isPresent()){
            return this.habitacionImple.habitacionPorTipo(Tipo);
        }else{
            throw new HabitacionValidoException("El tipo de habitacion no es Standar o Premiun ");
        }

    }
}
