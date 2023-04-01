package com.example.reservas.Service;

import com.example.reservas.ClasesDto.ReservaDto;
import com.example.reservas.Exception.ReservaInvalidoException;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import com.example.reservas.Repository.ClienteImple;
import com.example.reservas.Repository.ReservaImple;
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
    private ReservaDto reservaDto;



    @Autowired
    public ReservaService(ReservaImple reservaImple, ClienteImple clienteImple, ReservaDto reservaDto) {
        this.reservaImple = reservaImple;
        this.clienteImple = clienteImple;
        this.reservaDto = reservaDto;
    }

    @Override
    public ReservaDto create(ReservaDto reservaDto) {

        LocalDate fechaActual = LocalDate.now();
        Date fechaReservaCreate = reservaDto.getFecha();

        Integer idCliente = Optional.ofNullable(reservaDto.getDocumento_identidad())
                .map(Integer::valueOf)
                .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, valide que la reserva tenga un cliente asociado"));
        LocalDate fechaReserva = Optional.ofNullable(reservaDto.getFecha())
                .map(Date::toLocalDate)
                .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, valide que la reserva tenga una fecha de reserva"));

        Integer habitacion = Optional.ofNullable(reservaDto.getNumero_habitacion())
                .map(Integer::valueOf)
                .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, valide que la reserva tenga una habitación asociada"));

        Cliente cliente = Optional.ofNullable(this.clienteImple.cliente(idCliente))
                .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, el cliente asociado no existe en la base de datos"));

        if (fechaReserva.isBefore(fechaActual)) {
            throw new ReservaInvalidoException("No se pudo crear la reserva, valide  que la reserva tenga todos los parametros  y que la fecha sea igual a hoy o superior ");
        }
        // Verificar si la habitación está disponible para la fecha de reserva
        List<Habitacion> habitaciones = Optional.of(reservaImple.findByDateDisponibilidad(fechaReservaCreate))
                .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, no hay habitaciones disponibles para la fecha de reserva"));

        boolean confirmacion = habitaciones.stream().anyMatch(h -> h.getNumero_habitacion().equals(habitacion));
        if (!confirmacion) {
            throw new ReservaInvalidoException("La habitacion seleccionada para esa fecha esta reservada ");

        }

        return this.reservaImple.create(reservaDto);

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
    public Reserva reserva(Integer idReserva) {

        try {
            return this.reservaImple.reserva(idReserva);
        } catch (Exception e) {
            throw new ReservaInvalidoException("No existe el cliente  en la base de datos");
        }


    }


    public List<Habitacion> FindbyDateTypeRoom(Date fecha, String tipoHabitacion) {

        if (fecha == null || tipoHabitacion == (null)) {
            throw new ReservaInvalidoException("los Campos estan null el Patvariable y RequestParan son /{fechaReserva}/?tipoHabitacion= ");
        }
        return this.reservaImple.FindbyDateTypeRoom(fecha, tipoHabitacion);

    }


    public List<Habitacion> findByDateDisponibilidad(Date fecha) {

        if (fecha == null) {
            throw new ReservaInvalidoException("La fecha ingresada debe ser RequesParent es ?fecha=yyyymmdd ");
        }
        return this.reservaImple.findByDateDisponibilidad(fecha);

    }

    public List<Cliente> ClientesConReserva(Integer cedula) {
        if (cedula == null || !cedula.toString().matches("\\d+")) {
            throw new ReservaInvalidoException("La cedula esta errada o null , solo puede ser numerica");
        }
        return this.reservaImple.ClientesConReserva(cedula);
    }


}
