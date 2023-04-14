package com.example.reservas.Repository;


import com.example.reservas.Model.Cliente;

import java.util.*;

public interface ClienteDao {


    List<Cliente> createsArray(Cliente[] clientes);
    Cliente create(Cliente cliente);

    Boolean delete(int idCliente);
    List<Cliente> clienteAlll();

    Cliente cliente(int idCliente);


    List<Cliente>  customerByEgeMayorOrEqual(int edad);
}
