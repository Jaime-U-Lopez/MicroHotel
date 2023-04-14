package com.example.reservas.Service;

import com.example.reservas.Model.Cliente;

import java.util.List;

public interface ClienteServicesMetodos {


    List<Cliente>  createArray(Cliente[] cliente);
    Cliente  create(Cliente cliente);
    boolean delete(Integer idCliente);
    List<Cliente> clienteAlll();

    Cliente cliente(Integer idCliente);

    List<Cliente> customerByEgeMayorOrEqual (Integer edad);


}
