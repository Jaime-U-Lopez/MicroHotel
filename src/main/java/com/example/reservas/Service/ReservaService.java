package com.example.reservas.Service;

import com.example.reservas.Dto.ReservaDto;
import com.example.reservas.Exception.ClienteInvalidoException;
import com.example.reservas.Exception.ReservaInvalidoException;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import com.example.reservas.Repository.ClienteImple;
import com.example.reservas.Repository.HabitacionImple;
import com.example.reservas.Repository.ReservaImple;
import com.example.reservas.Util.ValidationMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService implements ReservaServiceMetodos {


    private ReservaImple reservaImple;
    private ClienteImple clienteImple;
    //private HabitacionService habitacionService;
    private HabitacionImple habitacionImple;



    @Autowired
    public ReservaService(ReservaImple reservaImple, ClienteImple clienteImple, HabitacionImple habitacionImple) {
        this.reservaImple = reservaImple;
        this.clienteImple = clienteImple;
        this.habitacionImple= habitacionImple;
    }

    @Override
    public ReservaDto create(ReservaDto reservaDto)   {


        LocalDate fechaActual = LocalDate.now();
        Date fechaActualForDate = Date.valueOf(fechaActual);
        Date fechaReservaCreate = reservaDto.getFechaReserva();


        Integer idClienteValidacion = Optional.ofNullable(reservaDto.getDocumentoIdentidadCliente())
                .map(Integer::valueOf)
                .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, valide que la reserva tenga un cliente asociado"));
        LocalDate fechaReserva = Optional.ofNullable(reservaDto.getFechaReserva())
                .map(Date::toLocalDate)
                .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, valide que la reserva tenga una fecha de reserva"));

        if (fechaReservaCreate.before(fechaActualForDate) ) {
            throw new ReservaInvalidoException("No se pudo crear la reserva, valide  que la fecha sea igual a hoy o superior ");
        }

        // Verificar si la habitación está disponible para la fecha de reserva

        Optional<Cliente>  cliente =Optional.ofNullable(this.clienteImple.cliente(reservaDto.getDocumentoIdentidadCliente()));
       Optional<Habitacion>  habitacionOptional =Optional.ofNullable(this.habitacionImple.habitacion(reservaDto.getNumeroHabitacion()));


       if(habitacionOptional.isPresent() && cliente.isPresent()){

           List<Habitacion> disponiblesHabitaciones= findByDateRoomDisponibilidad(fechaReservaCreate);

           List<Habitacion> habitacionesDisponibles = disponiblesHabitaciones.stream()
                   .filter(habitacion -> habitacion.getNumero_habitacion().equals(reservaDto.getNumeroHabitacion()) )
                   .collect(Collectors.toList());

           if(!habitacionesDisponibles.isEmpty() ){
               this.reservaImple.create(reservaDto );

           }else{
               throw new ReservaInvalidoException("Esta habitación no esta disponible");
           }

       }else{

           throw new ReservaInvalidoException("Este cliente  o la  Habitacion no esta registrado");
       }


        return reservaDto;

    }



    @Override
    public boolean delete(Integer idReserva) throws RuntimeException{

            return this.reservaImple.delete(idReserva);


    }

    @Override
    public List<Reserva> reservaAll() {


        try {
            return this.reservaImple.reservaAll();
        } catch (Exception e) {
            throw new ReservaInvalidoException("No existen clientes en la base de datos");
        }

    }

    @Override
    public Reserva reserva(Integer idReserva) throws RuntimeException {

        try {
            return this.reservaImple.reserva(idReserva);
        } catch (Exception e) {
            throw new ReservaInvalidoException(" No existe el cliente : "+idReserva +  " en la base de datos");
        }


    }

    @Override
    public List<Habitacion> FindbyDateByTypeRoom(Date fecha, String tipoHabitacion) {

        if (fecha == null || tipoHabitacion == (null)) {
            throw new ReservaInvalidoException("los Campos estan null el Patvariable y RequestParan son /{fechaReserva}/?tipoHabitacion= ");
        }
        return this.reservaImple.FindbyDateTypeRoom(fecha, tipoHabitacion);

    }

    @Override
    public List<Habitacion> findByDateRoomDisponibilidad(Date fecha) throws RuntimeException {

        if (fecha == null) {
            throw new ReservaInvalidoException("La fecha ingresada debe ser RequesParent es ?fecha=yyyymmdd ");
        }
        return this.reservaImple.findByDateDisponibilidad(fecha);

    }
    @Override
    public List<Cliente> ClientesConReserva(Integer cedula) throws RuntimeException {
        if (cedula == null || !cedula.toString().matches("\\d+")) {
            throw new ReservaInvalidoException("La cedula esta errada o null , solo puede ser numerica");
        }
        return this.reservaImple.ClientesConReserva(cedula);
    }




}
