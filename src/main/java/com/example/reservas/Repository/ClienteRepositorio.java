package com.example.reservas.Repository;

import com.example.reservas.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio  extends JpaRepository<Cliente, Integer > {


   // Cliente findByCedula(String cedula);

}
