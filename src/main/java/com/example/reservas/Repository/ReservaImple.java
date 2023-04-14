package com.example.reservas.Repository;

import com.example.reservas.Dto.ReservaDto;
import com.example.reservas.Exception.ReservaInvalidoException;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservaImple implements ReservasDao {

    private ReservaRepositorio reservaRepositorio;

    private ClienteRepositorio clienteRepositorio;

    @Autowired
    public ReservaImple(ReservaRepositorio reservaRepositorio, ClienteRepositorio clienteRepositorio) {
        this.reservaRepositorio = reservaRepositorio;
        this.clienteRepositorio = clienteRepositorio;
    }


    @Override
    public Reserva create(ReservaDto reservaDto) throws RuntimeException {

        Integer idCliente=reservaDto.getDocumentoIdentidadCliente();
        Integer numeroHabitacion= reservaDto.getNumeroHabitacion();

        Cliente documentoIdentidad = Optional.ofNullable(idCliente)
                .map(dni -> new Cliente(dni))
                .orElseThrow(() -> new ReservaInvalidoException("El Cliente  con id : "+idCliente+ " no existe en base de datos"));

        Habitacion habitacion = Optional.ofNullable(numeroHabitacion)
                .map(dni -> new Habitacion(dni))
                .orElseThrow(() -> new ReservaInvalidoException("La habitacion "+ numeroHabitacion+ "no existe en la base de datos "));

        Reserva reserva = new Reserva(
                reservaDto.getFechaReserva()
                , documentoIdentidad
                , habitacion
        );

        return this.reservaRepositorio.save(reserva);

    }


    @Override
    public boolean delete(Integer idReserva) throws RuntimeException {

        boolean existe = this.reservaRepositorio.existsById(idReserva);
        if (existe) {
            this.reservaRepositorio.deleteById(idReserva);
            return true;
        }
        return false;
    }

    @Override
    public List<Reserva> reservaAll() {

        List<Reserva> ListReserva = new ArrayList<>();
        this.reservaRepositorio.findAll().forEach(item -> ListReserva.add(item));
        return ListReserva;
    }

    @Override
    public Reserva reserva(Integer idReserva) {
        Optional<Reserva> reserva = this.reservaRepositorio.findById(idReserva);
        return reserva.get();
    }


    @Override
    public List<Habitacion> FindbyDateTypeRoom(Date fecha, String TipoHabitacion) {
        return reservaRepositorio.findByDateTypeRoom(fecha, TipoHabitacion);

    }

    @Override
    public List<Habitacion> findByDateDisponibilidad(Date fecha) {
        return this.reservaRepositorio.findByDateDisponibilidad(fecha);
    }

    @Override
    public List<Cliente> ClientesConReserva(Integer cedula) {
        return this.reservaRepositorio.clienteConReserva(cedula);
    }


}
