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
    @Column(name = "codigo_reserva", nullable = false )
    private Integer codigo_reserva;


    @Column(name = "fecha_Reserva", nullable = false)
    private Date fecha_Reserva;

    @ManyToOne
    @JoinColumn(name = "cedula")
    @JsonIgnoreProperties("reserva")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "habitacion")
    @JsonIgnoreProperties("reserva")
    private Habitacion habitacion ;


    @Column(name = "total_pagar", nullable = false)
    private Double total_pagar;
    public Reserva(){};

    public Reserva( Date fecha_Reserva, Cliente cliente, Habitacion habitacion) {
        this.fecha_Reserva = fecha_Reserva;
        this.cliente = cliente;
        this.habitacion = habitacion;

    }

    public Cliente getCliente() {
        return cliente;
    }



     public Date getFecha_Reserva() {
        return fecha_Reserva;
    }



    public Habitacion getHabitacion() {
      return habitacion;
 }

    public Integer getCodigo_reserva() {
        return codigo_reserva;
    }

    public Double getTotal_pagar() {
        return getHabitacion().getPrecio();
    }
}
