package com.example.reservas.Repository;

import com.example.reservas.ClasesDto.ReservaDto;
import com.example.reservas.Exception.ReservaInvalidoException;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.*;

@Repository
public class ReservaImple  implements ReservasDao {

    private ReservaRepositorio reservaRepositorio;

    private ClienteRepositorio clienteRepositorio;
    @Autowired
    public ReservaImple(ReservaRepositorio reservaRepositorio, ClienteRepositorio clienteRepositorio) {
        this.reservaRepositorio = reservaRepositorio;
        this.clienteRepositorio = clienteRepositorio;
    }






    @Override
    public Reserva create(ReservaDto reservaDto) {

       Cliente  documentoIdentidad = Optional.ofNullable(reservaDto.getDocumento_identidad())
               .map(dni -> new Cliente(dni))
               .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, valide que la reserva tenga un cliente asociado"));

        Habitacion habitacion = Optional.ofNullable(reservaDto.getNumero_habitacion())
                .map(dni -> new Habitacion(dni))
                .orElseThrow(() -> new ReservaInvalidoException("No se pudo crear la reserva, valide que la reserva tenga un habitacion  asociado"));

        Reserva reserva = new Reserva(
                reservaDto.getFecha()
                ,documentoIdentidad
                ,habitacion
                );

        return  this.reservaRepositorio.save(reserva);

    }




    @Override
    public boolean delete(Integer idReserva) {

        boolean existe=  this.reservaRepositorio.existsById(idReserva);
        if(existe){
            this.reservaRepositorio.deleteById( idReserva);
            return true;
        }
        return false;
    }

    @Override
    public List<Reserva> reservaAll() {

        List<Reserva>  ListReserva= new ArrayList<>();
        this.reservaRepositorio.findAll().forEach(item->ListReserva.add(item));
        return ListReserva;
    }

    @Override
    public Reserva reserva(Integer idReserva) {
        Optional<Reserva>  reserva=  this.reservaRepositorio.findById(idReserva);
        return reserva.get();
    }



    public List<Habitacion> FindbyDateTypeRoom(Date fecha , String TipoHabitacion){
        return reservaRepositorio.findByDateType(fecha, TipoHabitacion);

    }

    public List<Habitacion> findByDateDisponibilidad(Date fecha){
        return this.reservaRepositorio.findByDateDisponibilidad(fecha);
    }


        public List<Cliente>  ClientesConReserva(Integer cedula){
        return this.reservaRepositorio.clienteConReserva(cedula);
        }


}
