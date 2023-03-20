package com.project.finalprojectbackend.controller;

import com.example.clase22.entities.Odontologo;
import com.example.clase22.servicio.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public List<Odontologo> buscarAllOdontologos(){
        return odontologoService.buscarTodosOdontologos();
    }
    @PostMapping
    public Odontologo registrarNuevoOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoXId(id);
        if (odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoXId(id);
        if (odontologoBuscado.isPresent()){
            //puedo eliminarlo sin problemas porque existe
            odontologoService.borrarOdontologo(id);
            return ResponseEntity.ok("Se eliminó al odontólogo con id="+id);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el odontólogo con id" +
                    "="+id+" por lo que no se pudo realizar la eliminación. :(");
        }
    }
    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo){
        //controlamos aqui el id
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoXId(odontologo.getId());
        if (odontologoBuscado.isPresent()){
            //existe el id, puedo actualizarlo sin problema
            Odontologo odontologoActualizado=odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok(odontologoActualizado);
        }
        else{
            //que id no existe, no deberiamos poder realizar una actualización
            return ResponseEntity.badRequest().build();
        }
    }
}
