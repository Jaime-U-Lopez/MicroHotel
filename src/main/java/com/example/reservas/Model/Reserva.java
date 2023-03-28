package com.example.reservas.Model;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "reservas")
public class Reserva  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva", nullable = false )
    private Long codigo_reserva;


    @Column(name = "fecha_Reserva", nullable = false)
    private Date fecha_Reserva;


    @JoinColumn(name = "cliente")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cliente cliente;


    @ManyToOne
    @JoinColumn(name = "habitacion")
    private Habitacion habitacion ;


    @Column(name = "total_pagar", nullable = false)
    private Double total_pagar;
    public Reserva(){};

    public Reserva(Long codigo_reserva, Date fecha_Reserva, Cliente cliente, Habitacion habitacion, Double total_pagar) {
        this.codigo_reserva = codigo_reserva;
        this.fecha_Reserva = fecha_Reserva;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.total_pagar = total_pagar;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Long getCodigo_reserva() {
        return codigo_reserva;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

     public Date getFecha_Reserva() {
        return fecha_Reserva;
    }



public Habitacion getHabitacion() {
      return habitacion;
 }

    public double getTotal_pagar() {
        return total_pagar;
    }



    public void setFecha_Reserva(Date fecha_Reserva) {
        this.fecha_Reserva = fecha_Reserva;
    }



}
