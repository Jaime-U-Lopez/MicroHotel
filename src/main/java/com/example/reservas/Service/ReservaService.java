package com.example.reservas.Service;

import com.example.reservas.Model.Cliente;
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
    public String create(Reserva reserva) {

        int idCliente = reserva.getCliente().getId_Cliente();
        var cliente=  clienteImple.cliente(idCliente);
        Long reserva_codigo= reserva.getCodigo_reserva();
        Date fecha_Reserva= reserva.getFecha_Reserva();
        int habita = reserva.getHabitacion().getNumero();
        int valorHabitacion= reserva.getHabitacion().getPrecio();
        String TipoHabitacion = reserva.getHabitacion().getTipoHabitacion();
        double totalPago=0;

        if(TipoHabitacion.equals("premiun")){
            totalPago= totalApagar(valorHabitacion);
        }else{
            totalPago=valorHabitacion;
        }


        Date fecha = reserva.getFecha_Reserva();
        LocalDate fechaReserva = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String fechaActualFormateada = fechaActual.format(formatter);

        if (cliente != null && habita !=0 && fecha != null && !fechaReserva.isBefore(fechaActual) ) {
             this.reservaImple.create(reserva);
        }


     return "{"+"Total Pago :" + totalPago +
                 "Reserva Codigo :" +reserva_codigo +
                 "Numero Habitacion :"+habita +
                "Fecha Reserva :"+ fecha_Reserva + '\'' +
                '}';

    }





    @Override
    public boolean delete(Long idReserva) {
        return this.reservaImple.delete(idReserva);
    }

    @Override
    public List<Reserva> reservaAll() {
        return this.reservaImple.reservaAll();
    }

    @Override
    public Reserva reserva(Long idReserva) {
        return this.reservaImple.reserva(idReserva);
    }

    @Override
    public List<Reserva> queryConsultaPorFechaYHabitacion(String tipoHabitacion,String fecha) {
        return this.reservaImple.QueryConsultaPorFechaYHabitacion(tipoHabitacion,fecha);
    }

    @Override
    public Double totalApagar(int valorBaseHabitacion) {

        double totalApagar=(valorBaseHabitacion*0.05);
        return totalApagar;
    }
}
