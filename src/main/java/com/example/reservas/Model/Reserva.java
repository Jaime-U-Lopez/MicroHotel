package com.example.reservas.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "reservas")
public class Reserva  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_Reserva", nullable = false )
    private Integer codigoReserva;


    @Column(name = "fecha_Reserva", nullable = false)
    private Date fechaReserva;

    //carga lenta en base de datos
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cedula")
    @JsonIgnoreProperties("reserva")
    private Cliente cliente;

    //carga Lenta   carga rapida con leasy
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "habitacion")
    @JsonIgnoreProperties("reserva")
    private Habitacion habitacion ;


    @Column(name = "total_pagar")
    private Double totalPagar;
    public Reserva(){};

    public Reserva(Integer codigo_reserva) {
        this.codigoReserva = codigo_reserva;
    }

    public Reserva(Date fecha_Reserva, Cliente cliente, Habitacion habitacion) {
        this.fechaReserva = fecha_Reserva;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.totalPagar=0.0;

    }

    public Cliente getCliente() {
        return cliente;
    }


    public Integer getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(Integer codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }
}
