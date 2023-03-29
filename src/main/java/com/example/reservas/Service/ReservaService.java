package com.example.reservas.Service;

import com.example.reservas.Exception.ClienteInvalidoException;
import com.example.reservas.Exception.ReservaInvalidoException;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import com.example.reservas.Repository.ClienteImple;
import com.example.reservas.Repository.ReservaImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService  implements ReservaServiceMetodos{


    private ReservaImple reservaImple;

    private ClienteImple clienteImple;



    @Autowired
    public ReservaService(ReservaImple reservaImple, ClienteImple clienteImple) {
        this.reservaImple = reservaImple;
        this.clienteImple = clienteImple;
    }

    @Override
    public Reserva create(Reserva reserva) {


        Integer idCliente= reserva.getCliente().getCedula();
        LocalDate fechaReserva=reserva.getFecha_Reserva().toLocalDate();
        LocalDate fechaActual = LocalDate.now();
        Date fechaReservaConsulta=reserva.getFecha_Reserva();
        Integer habitacion= reserva.getHabitacion().getNumero_habitacion();

            Optional<Cliente> cliente= Optional.ofNullable(this.clienteImple.cliente(idCliente));
            Optional<List<Habitacion>> habitaciones= Optional.of(this.reservaImple.findByDateDisponibilidad(fechaReservaConsulta));
            boolean confirmacion= habitaciones.stream().equals(habitacion);


            if(!cliente.isPresent() || fechaReserva.equals(null) || fechaReserva.isBefore(fechaActual)   ){
                throw new ReservaInvalidoException("No se pudo crear la reserva, valide  que la reserva tenga todos los parametros  y que la fecha sea igual a hoy o superior ");
            }
            if(!confirmacion){
                throw new ReservaInvalidoException("La habitacion seleccionada para esa fecha esta reservada ");

            }
            return this.reservaImple.create(reserva);


    }


    @Override
    public boolean delete(Integer idReserva) {

        try{
            return this.reservaImple.delete(idReserva);
        }catch (Exception e){
            throw new ReservaInvalidoException("El cliente no se pudo eliminar, el id no existe");
        }

    }

    @Override
    public List<Reserva> reservaAll() {


        try{
            return this.reservaImple.reservaAll();
        }catch (Exception e){
            throw new ReservaInvalidoException("No existen clientes en la base de datos");
        }



    }

    @Override
    public Reserva reserva(Integer idReserva) {


        try{
            return this.reservaImple.reserva(idReserva);
        }catch (Exception e){
            throw new ReservaInvalidoException("No existe el cliente  en la base de datos");
        }



    }




    public List<Habitacion> FindbyDateTypeRoom(Date fecha, String tipoHabitacion){

        if(fecha.equals(null)||tipoHabitacion.equals(null)   ){
            throw new ReservaInvalidoException("los Campos estan null el Patvariable y RequestParan son /{fechaReserva}/?tipoHabitacion= ");
        }
        return this.reservaImple.FindbyDateTypeRoom(fecha, tipoHabitacion);

    }



    public List<Habitacion> findByDateDisponibilidad(Date fecha){

        if(fecha.equals(null)  ){
            throw new ReservaInvalidoException("La fecha ingresada debe ser RequesParent es ?fecha=yyyymmdd ");
        }
       return  this.reservaImple.findByDateDisponibilidad(fecha);

    }




    public List<Cliente> ClientesConReserva(Integer cedula){
        return this.reservaImple.ClientesConReserva(cedula);
    }

}
