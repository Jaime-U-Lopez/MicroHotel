package com.example.reservas.Service;

import com.example.reservas.Exception.ClienteInvalidoException;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Repository.ClienteImple;
import com.example.reservas.Util.ValidationMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ClienteServicesMetodos {


    private final ClienteImple clienteImple;
    @Autowired
    public ClienteService(ClienteImple clienteImple) {
        this.clienteImple = clienteImple;
    }

    @Override
    public List<Cliente> createArray(Cliente[] cliente) {
        return clienteImple.createsArray(cliente);
    }

    @Override
    public Cliente create(Cliente cliente) throws RuntimeException {

        Integer cedula = cliente.getCedula();
        String apellido = cliente.getApellido();
        String nombre = cliente.getNombre();
        Integer edad= cliente.getEdad();
        String  email= cliente.getCorreo_electronico();

        validarCliente(cedula,apellido,nombre,edad,email);
            return this.clienteImple.create(cliente);


    }


    private void validarCliente(Integer cedula, String apellido, String nombre, Integer edad, String correoElectronico) throws RuntimeException {
        if (apellido == null || nombre == null || cedula < 0  || !(cedula instanceof Integer)) {
            throw new ClienteInvalidoException("Creacion del  cliente con cc : " + cedula + "no cumple los parametros, de apellido, nombre y cedula en integer");
        }

        if (!ValidationMail.esMailValido(correoElectronico)) {
            throw new ClienteInvalidoException("El email : " + correoElectronico + " no es correcto, no cumple la estructura valida ");
        }

        if (edad < 18) {
            throw new ClienteInvalidoException("La Edad : " + edad + " no puede ser menor a 18 años ");
        }

        if (clienteImple.cliente(cedula) != null) {
            throw new ClienteInvalidoException("El cliente con cedula: " + cedula + "  ya existe en la base de datos");
        }
    }


    @Override
    public boolean delete(Integer idCliente) throws RuntimeException  {


        this.cliente(idCliente);
        Optional<Boolean> cliente = Optional.ofNullable(this.clienteImple.delete(idCliente));

        return true;

    }

    @Override
    public List<Cliente> clienteAlll() throws RuntimeException {

          Optional<List<Cliente>> clienteList=  Optional.ofNullable(this.clienteImple.clienteAlll());
          if(!clienteList.isPresent()){
              throw new ClienteInvalidoException("La base de datos no tiene clientes para mostrar");
        }
          return clienteList.get();
    }

    @Override
    public Cliente cliente(Integer idCliente) throws RuntimeException {



        Optional<Cliente> clienteOptional= Optional.ofNullable(this.clienteImple.cliente(idCliente));
        if(!clienteOptional.isPresent()){
            throw new ClienteInvalidoException("El Cliente con cc: " +idCliente+" no existe en la base de datos ");
        }
        return this.clienteImple.cliente(idCliente);


    }

    @Override
    public List<Cliente> customerByEgeMayorOrEqual(Integer edad) throws RuntimeException {

        if (edad < 18) {
            throw new ClienteInvalidoException("La edad : " + edad + " no puede ser menor a 18 años ");
        }
        return  this.clienteImple.customerByEgeMayorOrEqual(edad);
    }
}
