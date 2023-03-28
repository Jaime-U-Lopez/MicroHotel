package com.example.reservas.Repository;


import com.example.reservas.Model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;



@Repository
public class ClienteImple implements ClienteDao {

    private ClienteRepositorio clienteRepositorio;

    @Autowired
    public ClienteImple(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }


    @Override
    public List<Cliente> createsArray(Cliente[] clientes) {
        List<Cliente>  cliente= new ArrayList<>();
        for (Cliente item : clientes ) {
            cliente.add(item);
        }
        return cliente;
    }

    @Override
    public Cliente create(Cliente cliente)  {
        return this.clienteRepositorio.save(cliente);

    }

    @Override
    public boolean delete(int idCliente) {

        boolean existencia = this.clienteRepositorio.existsById(idCliente);
        if (existencia) {
            this.clienteRepositorio.deleteById(idCliente);
            return true;
        }
        return false;
    }

    @Override
    public List<Cliente> clienteAlll() {

        List<Cliente> listCliente= new ArrayList<>();
        try {
            this.clienteRepositorio.findAll().forEach(item-> listCliente.add(item));
            return  listCliente;
        }catch (Exception ex){
            throw new RuntimeException("La lista no existe, no hay clientes " +
                    "en la base de datos o no se esta conectando ");
        }
    }

    @Override
    public Cliente cliente(int idCliente) {

        try {
            Optional<Cliente> cliente= this.clienteRepositorio.findById(idCliente);
            return cliente.get();
        }catch (Exception e){
            throw new RuntimeException("El cliente no existe en la base de datos");

        }
    }
}
