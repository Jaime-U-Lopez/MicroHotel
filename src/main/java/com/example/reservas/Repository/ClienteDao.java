package com.example.reservas.Repository;


import com.example.reservas.Model.Cliente;

import java.util.*;

public interface ClienteDao {


    List<Cliente> createsArray(Cliente[] clientes);
    Cliente create(Cliente cliente);

    boolean delete(int idCliente);
    List<Cliente> clienteAlll();

    Cliente cliente(int idCliente);
}
