package com.example.reservas.ClasesDto;

import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Service
public class ReservaDto {

    @Id
    private Integer id;
    private Integer numero_habitacion;
    private Date Fecha;
    private Integer documento_identidad;


    public ReservaDto( int numero_habitacion, Date fecha, int documento_identidad) {
        this.numero_habitacion = numero_habitacion;
        Fecha = fecha;
        this.documento_identidad = documento_identidad;

    }

    public Integer getNumero_habitacion() {
        return numero_habitacion;
    }

    public void setNumero_habitacion(Integer numero_habitacion) {
        this.numero_habitacion = numero_habitacion;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public Integer getDocumento_identidad() {
        return documento_identidad;
    }

    public void setDocumento_identidad(Integer documento_identidad) {
        this.documento_identidad = documento_identidad;
    }

    public Integer getId() {
        return id;
    }
}
