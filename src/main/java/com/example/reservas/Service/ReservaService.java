package com.example.reservas.Service;

import com.example.reservas.Dto.ReservaDto;
import com.example.reservas.Exception.ClienteInvalidoException;
import com.example.reservas.Exception.ReservaInvalidoException;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import com.example.reservas.Repository.ClienteImple;
import com.example.reservas.Repository.ReservaImple;
import com.example.reservas.Util.ValidationMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService implements ReservaServiceMetodos {


    private ReservaImple reservaImple;
    private ClienteImple clienteImple;
    private HabitacionService habitacionService;



    @Autowired
    public ReservaService(ReservaImple reservaImple, ClienteImple clienteImple, HabitacionService habitacionService ) {
        this.reservaImple = reservaImple;
        this.clienteImple = clienteImple;
        this.habitacionService= habitacionService;
    }

    @Override
    public ReservaDto create(ReservaDto reservaDto)   {

        LocalDate fechaActual = LocalDate.now().minusDays(1);
        Date fechaReservaCreate = reservaDto.getFechaReserva();


        Integer idClienteValidacion = Optional.ofNullable(reservaDto.getDocumentoIdentidadCliente())
                .map(Integer::valueOf)
                .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, valide que la reserva tenga un cliente asociado"));
        LocalDate fechaReserva = Optional.ofNullable(reservaDto.getFechaReserva())
                .map(Date::toLocalDate)
                .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, valide que la reserva tenga una fecha de reserva"));


        if (fechaReserva.isBefore(fechaActual) ) {
            throw new ReservaInvalidoException("No se pudo crear la reserva, valide  que la fecha sea igual a hoy o superior ");
        }
        // Verificar si la habitación está disponible para la fecha de reserva
        List<Habitacion> habitaciones = Optional.of(reservaImple.findByDateDisponibilidad(fechaReservaCreate))
                .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, no hay habitaciones disponibles para la fecha de reserva"));

        Optional<Habitacion>  habitacion =Optional.ofNullable(  this.habitacionService.habitacion(reservaDto.getNumeroHabitacion()));
        if( habitacion.isPresent()){
            Boolean  confirmacion = habitaciones.stream()
                    .anyMatch(h -> h.getNumero_habitacion().equals(reservaDto.getNumeroHabitacion()));
            // .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, valide que la reserva tenga una fecha de reserva"));
            if (!confirmacion) {
                throw new ReservaInvalidoException("La habitacion seleccionada "+ reservaDto.getNumeroHabitacion()+" para esa fecha esta reservada ");
            }
        }


        if(!habitacion.isPresent()){
            throw new ReservaInvalidoException("La habitacion seleccionada "+ reservaDto.getNumeroHabitacion()+" no existe en la base de datos ");

        }
            this.reservaImple.create(reservaDto);


        return reservaDto;

    }











    @Override
    public boolean delete(Integer idReserva) {

        try {
            return this.reservaImple.delete(idReserva);
        } catch (Exception e) {
            throw new ReservaInvalidoException("El cliente no se pudo eliminar, el id no existe");
        }

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
