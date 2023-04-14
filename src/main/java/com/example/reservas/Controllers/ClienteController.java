package com.example.reservas.Controllers;

import com.example.reservas.Model.Cliente;
import com.example.reservas.Service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/")
@Api(tags = "Cliente", description = "Operaciones para gestionar un cliente")
public class ClienteController {


    private ClienteService clienteService;
    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }



    @ApiResponses(value = {
            @ApiResponse(description = "400 - Bad Request", responseCode = "400"),
            @ApiResponse(description = "200 - Request successful", responseCode = "200"),
            @ApiResponse(description = "401 - Unauthorized", responseCode = "401"),
            @ApiResponse(description = "403 - Access Denied", responseCode = "403"),
            @ApiResponse(description = "404 - Not Found", responseCode = "404"),
            @ApiResponse(description = "500 - Internal error, please validate the entered fields", responseCode = "500")
    })
    @Operation(summary = "Crear un cliente")
    @PostMapping("clientes")
    public ResponseEntity<?> createCliente(@NotNull @Valid @RequestBody Cliente cliente)  {

            Cliente nuevoCliente = clienteService.create(cliente);
            return ResponseEntity.ok(nuevoCliente);

    }



    @ApiResponses(value = {
            @ApiResponse(description = "400 - Bad Request", responseCode = "400"),
            @ApiResponse(description = "200 - Request successful", responseCode = "200"),
            @ApiResponse(description = "401 - Unauthorized", responseCode = "401"),
            @ApiResponse(description = "403 - Access Denied", responseCode = "403"),
            @ApiResponse(description = "404 - Not Found", responseCode = "404"),
            @ApiResponse(description = "500 - Internal error, please validate the entered fields", responseCode = "500")
    })
    @Operation(summary = "Lista todos los clientes")
    @GetMapping("clientes")
    public List<Cliente> clientesAll() {
        return this.clienteService.clienteAlll();
    }



    @ApiResponses(value = {
            @ApiResponse(description = "400 - Bad Request", responseCode = "400"),
            @ApiResponse(description = "200 - Request successful", responseCode = "200"),
            @ApiResponse(description = "401 - Unauthorized", responseCode = "401"),
            @ApiResponse(description = "403 - Access Denied", responseCode = "403"),
            @ApiResponse(description = "404 - Not Found", responseCode = "404"),
            @ApiResponse(description = "500 - Internal error, please validate the entered fields", responseCode = "500")
    })
    @Operation(summary = "Mostrar cliente por ID")
    @GetMapping("clientes/")
    public ResponseEntity<?>  cliente(@RequestParam @ApiParam(value = "ID del cliente a consultar", example = "123") int idCliente)  {


        Optional<Cliente> cliente = Optional.ofNullable(this.clienteService.cliente(idCliente));
         return ResponseEntity.status(HttpStatus.CREATED).body(cliente.get());
    }


    @ApiResponses(value = {
            @ApiResponse(description = "400 - Bad Request", responseCode = "400"),
            @ApiResponse(description = "200 - Request successful", responseCode = "200"),
            @ApiResponse(description = "401 - Unauthorized", responseCode = "401"),
            @ApiResponse(description = "403 - Access Denied", responseCode = "403"),
            @ApiResponse(description = "404 - Not Found", responseCode = "404"),
            @ApiResponse(description = "500 - Internal error, please validate the entered fields", responseCode = "500")
    })
    @Operation(summary = "Mostrar cliente por Edad  mayor o igual")
    @GetMapping("clientes/{edad}")
    public ResponseEntity<?> clientePorEdadMayorOIgual(@PathVariable int edad)  {

        Optional<List<Cliente>> clientePorEdad = Optional.ofNullable(this.clienteService.customerByEgeMayorOrEqual(edad));
        if (clientePorEdad.isPresent()) {
            return ResponseEntity.ok(clientePorEdad.get());
        }
        String mensage = "no se puede traer la lista";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensage);

    }





    @ApiResponses(value = {
            @ApiResponse(description = "400 - Bad Request", responseCode = "400"),
            @ApiResponse(description = "200 - Request successful", responseCode = "200"),
            @ApiResponse(description = "401 - Unauthorized", responseCode = "401"),
            @ApiResponse(description = "403 - Access Denied", responseCode = "403"),
            @ApiResponse(description = "404 - Not Found", responseCode = "404"),
            @ApiResponse(description = "500 - Internal error, please validate the entered fields", responseCode = "500")
    })
    @Operation(summary = "Eliminar  cliente por ID")
    @DeleteMapping("clientes/{idcliente}")
    public ResponseEntity<?> delete(@PathVariable @ApiParam(value = "ID del cliente a eliminar", example = "123") int idcliente) {

        Boolean confirmacion= this.clienteService.delete(idcliente);
        if(confirmacion){
            String mensage= "El cliente con id : " + idcliente + " se elimino con Exito!";
            return ResponseEntity.ok(mensage);

        };
        String mensage = "No se puede eliminar el id "+ idcliente;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensage);



    }



}






