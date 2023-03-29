package com.example.reservas.Model;


import javax.persistence.*;

@Entity
@Table(name="confirmacionesReservas")
public class ConfirmacionReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Reserva reserva;

    public ConfirmacionReserva(){}
    public ConfirmacionReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Integer getId() {
        return id;
    }

    public Reserva getReserva() {
        return reserva;
    }


}
