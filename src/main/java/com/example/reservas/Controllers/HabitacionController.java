package com.example.reservas.Controllers;



import com.example.reservas.Model.Habitacion;
import com.example.reservas.Service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping("habitaciones")
    public List<Habitacion> habitacionAll(){
        return this.habitacionService.habitacionAll();
    }

   @GetMapping("habitaciones/{idHabitacion}")
   public Habitacion habitacion(@PathVariable int idHabitacion){
    return  this.habitacionService.habitacion(idHabitacion);
}
    @GetMapping("habitaciones/{tipo}")
    public List<Habitacion> habitacionPorTipo(@PathVariable String tipo){
        return  this.habitacionService.habitacionPorTipo(tipo);
    }




    @PostMapping("habitaciones")
    public Habitacion createHabitacion(@RequestBody Habitacion habitacion){
        return this.habitacionService.create(habitacion);
    }

    @DeleteMapping("habitaciones/{idHabitacion}")
    public boolean delete(@PathVariable int idHabitacion){
        return this.habitacionService.delete(idHabitacion);
    }

    @PutMapping("habitaciones")
    public void update(@RequestBody Habitacion habitacion){

    }







}
