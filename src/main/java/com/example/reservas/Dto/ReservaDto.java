package com.example.reservas.Dto;

import java.sql.Date;

public class ReservaDto {


    private Integer numeroHabitacion;
    private Date FechaReserva;
    private Integer documentoIdentidadCliente;


    public ReservaDto( int numero_habitacion, Date FechaReserva, int documento_identidad) {
        this.numeroHabitacion= numero_habitacion;
        this.FechaReserva = FechaReserva;
        this.documentoIdentidadCliente = documento_identidad;
    }


    public Integer getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(Integer numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public Date getFechaReserva() {
        return FechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        FechaReserva = fechaReserva;
    }

    public Integer getDocumentoIdentidadCliente() {
        return documentoIdentidadCliente;
    }

    public void setDocumentoIdentidadCliente(Integer documentoIdentidadCliente) {
        this.documentoIdentidadCliente = documentoIdentidadCliente;
    }


}
