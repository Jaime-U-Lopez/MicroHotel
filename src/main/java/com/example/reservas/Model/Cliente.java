package com.example.reservas.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="cedula", nullable = false)
    private Integer cedula;

    @Column(name="nombre", nullable = false)
    private String nombre;
    @Column(name="apellido" , nullable = false)
    private String apellido;
    @Column(name="direccion", nullable = false)
    private String direccion;
    @Column(name="edad", nullable = false)
    private int edad;

    @Column(name="correo_electronico", nullable = false)
    private String correo_electronico;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "cliente")
    @JsonIgnoreProperties("cliente")
    private List<Reserva> reserva;

    public Cliente(){};

    public Cliente(Integer cedula) {
        this.cedula = cedula;
    }

    public Cliente(Integer cedula, String nombre, String apellido, String direccion, int edad, String correo_electronico) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.edad = edad;
        this.correo_electronico = correo_electronico;
    }

    public int getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }



    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getEdad() {
        return edad;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }
}
