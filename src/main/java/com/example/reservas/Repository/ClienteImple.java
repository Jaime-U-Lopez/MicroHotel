package com.example.reservas.Repository;


import com.example.reservas.Exception.ClienteInvalidoException;
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
    public Boolean delete(int idCliente)  {


        this.clienteRepositorio.deleteById(idCliente);;
        return true;




    }

    @Override
    public List<Cliente> clienteAlll() throws RuntimeException {

        List<Cliente> listCliente= new ArrayList<>();
        try {
            this.clienteRepositorio.findAll().forEach(item-> listCliente.add(item));
            return  listCliente;
        }catch (Exception ex){
            throw new ClienteInvalidoException ("La lista no existe, no hay clientes " +
                    "en la base de datos o no se esta conectando ");
        }
    }

    @Override
    public Cliente cliente(int idCliente) throws RuntimeException {


            Optional<Cliente> cliente= this.clienteRepositorio.findById(idCliente);
        if (!cliente.isPresent()) {
          return null;
        }
            return cliente.get();

    }

    @Override
    public  List<Cliente> customerByEgeMayorOrEqual(int edad)  throws RuntimeException {

        Optional<List<Cliente>>  optionalClientes= Optional.ofNullable(this.clienteRepositorio.customerByEgeMayorOrEqual(edad));

        if(!optionalClientes.isPresent()){

            throw new ClienteInvalidoException("No Existe clientes con esa edad o mayores en la base de datos");
        }
        return this.clienteRepositorio.customerByEgeMayorOrEqual(edad);
    };



}
