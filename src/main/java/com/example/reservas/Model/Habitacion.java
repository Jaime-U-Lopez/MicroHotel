package com.example.reservas.Model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="habitaciones")
public class Habitacion   implements Serializable {


    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="numero_habitacion")
    private int numero_habitacion;


    @Column(name="tipo_Habitacion")
    private String tipoHabitacion;
    @Column(name="precio")
    private int precio;



    @OneToMany(mappedBy = "habitacion")
    private List<Reserva> reservas;


    public Habitacion(){};

    public Habitacion(int numero_habitacion, String tipoHabitacion, int precio) {
        this.numero_habitacion = numero_habitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precio = precio;
    }

    public int getNumero() {
        return numero_habitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public int getPrecio() {
        return precio;
    }


}
