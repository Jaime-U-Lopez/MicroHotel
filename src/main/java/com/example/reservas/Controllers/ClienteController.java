package com.example.reservas.Controllers;

import com.example.reservas.Model.Cliente;
import com.example.reservas.Service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("api/v1/")
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
    public ResponseEntity<Cliente> createCliente(@NotNull @Valid @RequestBody Cliente cliente) {

        Cliente clienteCreado = this.clienteService.create(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
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
    @Operation(summary = "Traer cliente por ID")
    @GetMapping("clientes/{idCliente}")
    public Cliente cliente(@PathVariable int idCliente) {
        return this.clienteService.cliente(idCliente);
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
    public ResponseEntity<Void> delete(@PathVariable int idcliente) {
        boolean clienteEliminado = this.clienteService.delete(idcliente);
        if (clienteEliminado) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}






