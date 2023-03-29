package com.example.reservas.Controllers;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("clientes")
    public List<Cliente> clientesAll() {
        return this.clienteService.clienteAlll();
    }

    @GetMapping("clientes/{idCliente}")
    public Cliente cliente(@PathVariable int idCliente) {
        return this.clienteService.cliente(idCliente);
    }

    @PostMapping("clientes")
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return this.clienteService.create(cliente);
    }




    @DeleteMapping("clientes/{idcliente}")
    public boolean delete(@PathVariable int idcliente) {
        return this.clienteService.delete(idcliente);
    }


    @PutMapping("clientes")
    public void update(@RequestBody Cliente cliente) {


    }


}
