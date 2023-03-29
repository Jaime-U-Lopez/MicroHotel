package com.example.reservas.Service;

import com.example.reservas.Exception.ClienteInvalidoException;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Repository.ClienteImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements ClienteServicesMetodos {


    private ClienteImple clienteImple;

    @Autowired
    public ClienteService(ClienteImple clienteImple) {
        this.clienteImple = clienteImple;
    }

    @Override
    public List<Cliente> createArray(Cliente[] cliente) {
        return clienteImple.createsArray(cliente);
    }

    @Override
    public Cliente create(Cliente cliente) {

        Integer cedula = cliente.getCedula();
        String apellid = cliente.getApellido();
        String nombre = cliente.getNombre();
        if (apellid == null || nombre == null || !(cedula instanceof Integer)) {
            throw new ClienteInvalidoException("Creacion del  cliente no cumple los parametros, de apellido, nombre y cedula en integer");
        }
        return this.clienteImple.create(cliente);

    }

    @Override
    public boolean delete(int idCliente) {

        try {
            return this.clienteImple.delete(idCliente);
        } catch (ClienteInvalidoException ex) {
            throw new ClienteInvalidoException("No se puedo eliminar el Cliente, valide el id ingresado");

        }
    }

    @Override
    public List<Cliente> clienteAlll() {

        try {
            return this.clienteImple.clienteAlll();
        } catch (ClienteInvalidoException ex) {
            throw new ClienteInvalidoException("La base de datos no tiene clientes");
        }


    }

    @Override
    public Cliente cliente(int idCliente) {

        try {
            return this.clienteImple.cliente(idCliente);

        } catch (ClienteInvalidoException ex) {
            throw new ClienteInvalidoException("El Cliente no existe en la base de datos ");

        }
    }
}
