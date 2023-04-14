package com.example.reservas.Repository;

import com.example.reservas.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepositorio  extends JpaRepository<Cliente, Integer > {


@Query("Select c from Cliente c where edad>=?1")
List<Cliente> customerByEgeMayorOrEqual(int edad);

}
