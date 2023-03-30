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


    @Column(name = "total_pagar", nullable = false)
    private Double total_pagar;
    public Reserva(){};

    public Reserva(Integer codigo_reserva) {
        this.codigo_reserva = codigo_reserva;
    }

    public Reserva(Date fecha_Reserva, Cliente cliente, Habitacion habitacion) {
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

    public void setCodigo_reserva(Integer codigo_reserva) {
        this.codigo_reserva = codigo_reserva;
    }

    public void setFecha_Reserva(Date fecha_Reserva) {
        this.fecha_Reserva = fecha_Reserva;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setTotal_pagar(Double total_pagar) {
        this.total_pagar = total_pagar;
    }
}
