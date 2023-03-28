package com.example.reservas.Service;

import com.example.reservas.Model.Cliente;
import com.example.reservas.Repository.ClienteImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService  implements ClienteServicesMetodos{


    private ClienteImple clienteImple;

    @Autowired
    public ClienteService(ClienteImple clienteImple) {
        this.clienteImple = clienteImple;
    }

    @Override
    public List<Cliente>  createArray(Cliente [] cliente) {
        return clienteImple.createsArray(cliente);
    }
    @Override
    public Cliente create(Cliente cliente) {

        Long cedula =cliente.getCedula();
        String apellid= cliente.getApellido();
        String nombre=cliente.getNombre();
        if(apellid != null || nombre != null || cedula instanceof Long){
            return this.clienteImple.create(cliente);
        }
    return cliente;

    }
    @Override
    public boolean delete(int idCliente) {
        return this.clienteImple.delete(idCliente);
    }

    @Override
    public List<Cliente> clienteAlll() {
        return this.clienteImple.clienteAlll();
    }

    @Override
    public Cliente cliente(int idCliente) {
        return this.clienteImple.cliente(idCliente);
    }
}
