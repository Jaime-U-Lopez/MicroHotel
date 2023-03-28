package com.example.reservas.Repository;

import com.example.reservas.Model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ReservaImple  implements ReservasDao {

    private ReservaRepositorio reservaRepositorio;
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    public ReservaImple(ReservaRepositorio reservaRepositorio) {
        this.reservaRepositorio = reservaRepositorio;

    }
    @Override
    public Reserva create(Reserva reserva) {

        return this.reservaRepositorio.save(reserva);

    }

    @Override
    public boolean delete(Long idReserva) {

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
    public Reserva reserva(Long idReserva) {
        Optional<Reserva>  reserva=  this.reservaRepositorio.findById(idReserva);
        return reserva.get();
    }

    @Override
    public List<Reserva> QueryConsultaPorFechaYHabitacion(String tipoHabitacion, String fecha) {
        return this.reservaRepositorio.findByTipoFechaYHabitacion(tipoHabitacion,fecha);
    }
}
