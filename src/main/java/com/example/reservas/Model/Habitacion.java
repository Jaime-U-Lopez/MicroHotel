package com.example.reservas.Model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="habitaciones")
public class Habitacion   implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="numero_habitacion", nullable = false)
    private Integer numero_habitacion;
    @Column(name="tipo_Habitacion", nullable = false)
    private String tipoHabitacion;
    @Column(name="precio", nullable = false)
    private Double precio;

    public Habitacion(){};

    public Habitacion(Integer numero_habitacion) {
        this.numero_habitacion = numero_habitacion;
    }

    public Habitacion(Integer numero_habitacion, String tipoHabitacion, Double precio) {
        this.numero_habitacion = numero_habitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precio = precio;
    }

    public Integer getNumero_habitacion() {
        return numero_habitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setNumero_habitacion(Integer numero_habitacion) {
        this.numero_habitacion = numero_habitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
