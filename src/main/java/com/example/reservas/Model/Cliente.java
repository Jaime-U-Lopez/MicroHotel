package com.example.reservas.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name="clientes")
public class Cliente implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cliente" , nullable = false )
    private int id_cliente;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="cedula")
    private Long cedula;
    @Column(name="direccion")
    private String direccion;
    @Column(name="edad")
    private int edad;


    @Column(name="correo_electronico")
    private String correo_electronico;


    @OneToMany(mappedBy = "cliente")
    private List<Reserva> Reservas ;


    public Cliente(){};

    public Cliente(int id_cliente) {

        this.id_cliente = id_cliente +2;
    }

    public Cliente(String nombre, String apellido, Long cedula, String direccion, int edad, String correo_electronico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.edad = edad;
        this.correo_electronico = correo_electronico;
        this.getId_Cliente();
    }

    public int getId_Cliente() {
        return id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cedula=" + cedula +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    public String getApellido() {
        return apellido;
    }

    public Long getCedula() {
        return cedula;
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
