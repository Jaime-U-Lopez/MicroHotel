/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.reservas;

import com.example.reservas.Exception.ReservaInvalidoException;
import com.example.reservas.Model.Cliente;
import com.example.reservas.Model.Habitacion;
import com.example.reservas.Model.Reserva;
import com.example.reservas.Repository.ClienteImple;
import com.example.reservas.Repository.HabitacionImple;
import com.example.reservas.Repository.ReservaImple;
import com.example.reservas.Service.ReservaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * @author jaime-lopez
 */
public class ReservaServiceTest {

    ReservaService reservaService;

    @Mock
    ClienteImple clienteImplMock;
    @Mock
    private HabitacionImple habitacionImplMock;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private ReservaImple reservaImplMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.reservaService = new ReservaService(reservaImplMock, clienteImplMock);
    }

    @Test
    public void deleteWithValidIdReservaTest() {
        Integer idReserva = 456;
        when(reservaService.delete(idReserva)).thenReturn(true);
        boolean result = reservaService.delete(idReserva);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteWithInvalidIdReservaTest() {
        Integer idReserva = 123;
        when(reservaService.delete(idReserva)).thenReturn(false);
        reservaService.delete(idReserva);
    }
@Test
    public void reservaSuccessTest() {
        // Mock del resultado de la implementación de ReservaImpl
        Reserva reservaMock = new Reserva();
        when(reservaImplMock.reserva(1)).thenReturn(reservaMock);
        // Llamada al método a testear
        Reserva result = reservaService.reserva(1);
        // Validación del resultado
        assertEquals(reservaMock, result);

        // Verificación de que se llamó una vez al método de ReservaImpl
        verify(reservaImplMock, times(1)).reserva(1);
    }
    @Test
    public void reservaFailureTest() {
        Integer idReserva = 1;
        Reserva expectedReserva = new Reserva(idReserva);
        when(reservaImplMock.reserva(idReserva)).thenReturn(expectedReserva);

        // When
        Reserva actualReserva = reservaService.reserva(idReserva);

        // Then
        assertEquals(expectedReserva, actualReserva);
        verify(reservaImplMock, times(1)).reserva(idReserva);

        // Given
        when(reservaImplMock.reserva(3)).thenThrow(new ReservaInvalidoException("No existe el cliente en la base de datos"));

}
@Test
    public void testReservaAll() {
        // Crear datos de prueba
    List<Reserva> reservas = new ArrayList<>();

    String fechaReservaStr= "2000-05-20";
    Date fechaReserva=Date.valueOf(fechaReservaStr);
        Reserva reserva1 = new Reserva(fechaReserva, new Cliente(1), new Habitacion(20));
        Reserva reserva2 = new Reserva(fechaReserva ,new Cliente(1),new Habitacion(20));
        reservas.add(reserva1);
        reservas.add(reserva2);

        // Configurar el comportamiento del mock
        when(reservaImplMock.reservaAll()).thenReturn(reservas);

        // Ejecutar el método a probar
        List<Reserva> resultado = this.reservaService.reservaAll();

        // Verificar el resultado
        assertEquals(reservas, resultado);
        verify(reservaImplMock, times(1)).reservaAll();
    }


    @Test(expected = ReservaInvalidoException.class)
    public void testReservaAllException() {
        // Configurar el comportamiento del mock
        when(reservaImplMock.reservaAll()).thenThrow(new ReservaInvalidoException("No existen clientes en la base de datos"));

        // Ejecutar el método a probar
        reservaService.reservaAll();
        // Validar el resultado (no se llegará a esta línea en caso de que se lance la excepción esperada)
        fail("Se esperaba una excepción ReservaInvalidoException");

    }



    @Test(expected = ReservaInvalidoException.class)
    public void testFindByDateDisponibilidadNull() {
        // Ejecutar el método a probar

        reservaService.findByDateDisponibilidad(null);

        // Validar el resultado (no se llegará a esta línea en caso de que se lance la excepción esperada)
        fail("Se esperaba una excepción ReservaInvalidoException");
    }



    @Test(expected = ReservaInvalidoException.class)
    public void testFindByDateDisponibilidadError() {

        // Configurar el comportamiento del mock
        when(reservaImplMock.findByDateDisponibilidad(any())).thenThrow(new ReservaInvalidoException("No se encontraron habitaciones disponibles"));

        // Ejecutar el método a probar
        String fechaReserva= "2000-05-20";
        Date fecha = Date.valueOf(fechaReserva);
        reservaService.findByDateDisponibilidad(fecha);
    }


    @Test
    public void testFindByDateDisponibilidadSuccess() {
        // Configurar el comportamiento del mock
        List<Habitacion> habitaciones = new ArrayList<>();
        habitaciones.add(new Habitacion(1, "premiun", 100.0));
        habitaciones.add(new Habitacion(2, "estandar", 150.0));
        when(reservaImplMock.findByDateDisponibilidad(any())).thenReturn(habitaciones);

        // Ejecutar el método a probar
        String fechaReserva= "2000-05-20";
        Date fecha = Date.valueOf(fechaReserva);
        List<Habitacion> result = reservaService.findByDateDisponibilidad(fecha);

        // Validar el resultado
        assertNotNull(result);
        assertEquals(2, result.size());
    }



    @Test
    public void testFindByDateTypeRoom() {
        // Crear una fecha válida y un tipo de habitación válido
        String fechaStr="2000-04-01";
        Date fecha =Date.valueOf(fechaStr);
        String tipoHabitacion = "tipoHabitacion";

        // Configurar el comportamiento del mock
        when(reservaImplMock.FindbyDateTypeRoom(fecha, tipoHabitacion)).thenReturn(List.of(new Habitacion()));

        // Ejecutar el método a probar
        List<Habitacion> habitaciones = reservaService.FindbyDateTypeRoom(fecha, tipoHabitacion);

        // Verificar que se llamó al método del mock una sola vez
        verify(reservaImplMock, times(1)).FindbyDateTypeRoom(fecha, tipoHabitacion);

        // Verificar que se obtuvo una lista de habitaciones no vacía
        assertNotNull(habitaciones);
        assertFalse(habitaciones.isEmpty());
    }

    @Test(expected = ReservaInvalidoException.class)
    public void testFindByDateTypeRoomTipoHabitacionNull()  {
        // Crear una fecha válida
        String fechaStr="2000-04-01";
        Date fecha =Date.valueOf(fechaStr);
        // Ejecutar el método a probar con tipoHabitacion null
        List<Habitacion>  reservasPorTipo= reservaService.FindbyDateTypeRoom(fecha, null);

        fail("Se esperaba una excepción ReservaInvalidoException");
    }


    @Test(expected = ReservaInvalidoException.class)
    public void testFindByDateTypeRoomFechaNull() {
        // Ejecutar el método a probar con fecha null
        reservaService.FindbyDateTypeRoom(null, "tipoHabitacion");
        fail("Se esperaba una excepción ReservaInvalidoException");
    }

    @Test(expected = ReservaInvalidoException.class)
    public void testCreateException1() {
        Reserva reserva = new Reserva();
        reserva.setCliente(null);
        reserva.setFecha_Reserva(null);
        reserva.setHabitacion(null);

        reservaService.create(reserva);
        fail("Se esperaba una excepción ReservaInvalidoException");
    }

    @Test
    public void testCreateReserva() {
        // crear un objeto Reserva para probar
        Reserva reserva = new Reserva();

       // reserva.setCodigo_reserva(1);
        String fechaStr="2023-04-01";
        Date fecha =Date.valueOf(fechaStr);
        reserva.setFecha_Reserva(fecha);

        // crear un objeto Cliente asociado a la reserva
        Cliente cliente = new Cliente();
        cliente.setCedula(1);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setEdad(25);
        cliente.setDireccion("Perez");
        cliente.setCorreo_electronico("juanperez@example.com");

        reserva.setCliente(cliente);
        // crear un objeto Habitacion asociado a la reserva
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero_habitacion(12);
        habitacion.setTipoHabitacion("simple");
        habitacion.setPrecio(100000.0);

        reserva.setHabitacion(habitacion);



        // mockear el comportamiento de los repositorios
        when(clienteImplMock.create(cliente)).thenReturn(cliente);
        when(clienteImplMock.cliente(cliente.getCedula())).thenReturn(cliente);
        when(habitacionImplMock.create(habitacion)).thenReturn(habitacion);
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        habitacionesDisponibles.add(habitacion);
        when(reservaImplMock.findByDateDisponibilidad(fecha)).thenReturn(habitacionesDisponibles);


        when(reservaImplMock.create(reserva)).thenReturn(reserva);
        // crear la reserva
        Reserva createdReserva = reservaService.create(reserva);

        // verificar que se ha creado la reserva correctamente
        assertNotNull(createdReserva);
        assertEquals(reserva, createdReserva);
    }

    @Test(expected = ReservaInvalidoException.class)
    public void testCreateReservaWithMissingReservationDate() {
        Reserva reserva = new Reserva();

        Cliente cliente = new Cliente();
        cliente.setCedula(1);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setEdad(25);
        cliente.setDireccion("Perez");
        cliente.setCorreo_electronico("juanperez@example.com");

        reserva.setCliente(cliente);

        Habitacion habitacion = new Habitacion();
        habitacion.setNumero_habitacion(12);
        habitacion.setTipoHabitacion("simple");
        habitacion.setPrecio(100000.0);

        reserva.setHabitacion(habitacion);

        when(reservaImplMock.findByDateDisponibilidad(any(Date.class))).thenReturn(Collections.singletonList(habitacion));
        when(clienteImplMock.create(cliente)).thenReturn(cliente);
        when(clienteImplMock.cliente(cliente.getCedula())).thenReturn(cliente);

        reservaService.create(reserva);

        fail("Se esperaba una excepción ReservaInvalidoException");
    }



    @Test(expected = ReservaInvalidoException.class)
    public void testCreateReservaWithMissingClient() {
        Reserva reserva = new Reserva();
        reserva.setFecha_Reserva(Date.valueOf("2023-04-01"));

        Habitacion habitacion = new Habitacion();
        habitacion.setNumero_habitacion(12);
        habitacion.setTipoHabitacion("simple");
        habitacion.setPrecio(100000.0);

        reserva.setHabitacion(habitacion);

        when(reservaImplMock.findByDateDisponibilidad(any(Date.class))).thenReturn(Collections.singletonList(habitacion));

        reservaService.create(reserva);

        fail("Se esperaba una excepción ReservaInvalidoException");
    }


    @Test
    public void testClientesConReserva() {
        // Crear un cliente con una reserva
        Cliente cliente = new Cliente();
        cliente.setCedula(123456);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setEdad(25);
        cliente.setDireccion("Perez");
        cliente.setCorreo_electronico("juanperez@example.com");

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setFecha_Reserva(Date.valueOf("2023-05-01"));
        reserva.setHabitacion(new Habitacion());

        // Mockear el comportamiento del repositorio para que devuelva el cliente con la reserva
        when(reservaImplMock.ClientesConReserva(123456)).thenReturn(Collections.singletonList(cliente));

        // Llamar al método a probar con la cédula del cliente creado
        List<Cliente> clientesConReserva = reservaService.ClientesConReserva(123456);

        // Verificar que se devolvió la lista con el cliente creado
        assertNotNull(clientesConReserva);
        assertEquals(1, clientesConReserva.size());
        assertEquals(cliente, clientesConReserva.get(0));
    }


    @Test(expected = ReservaInvalidoException.class)
    public void testClientesConReservaCedulaNula() {
        reservaService.ClientesConReserva(null);
        fail("Se esperaba una excepción ReservaInvalidoException");
    }


    @Test
    public void testClientesConReservaCedulaValidaSinClientes() {
        // Mockear el comportamiento del repository para retornar una lista vacía
        when(reservaImplMock.ClientesConReserva(1)).thenReturn(Collections.emptyList());

        // Llamar al método a probar y verificar que se retorna una lista vacía
        List<Cliente> clientes = reservaService.ClientesConReserva(1);
        assertNotNull(clientes);
        assertTrue(clientes.isEmpty());
    }

}