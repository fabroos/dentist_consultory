package com.project.finalprojectbackend.controller;

import com.example.clase22.dto.TurnoDTO;
import com.example.clase22.entities.Turno;
import com.example.clase22.exceptions.ResourceNotFoundException;
import com.example.clase22.servicio.OdontologoService;
import com.example.clase22.servicio.PacienteService;
import com.example.clase22.servicio.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno){
        ResponseEntity<TurnoDTO> respuesta;
        //cuando esta ok
        //registrar el turno porque ambos existen
        if (odontologoService.buscarOdontologoXId(turno.getOdontologo_id()).isPresent()
        &&pacienteService.buscar(turno.getPaciente_id()).isPresent()){
            respuesta=ResponseEntity.ok(turnoService.guardar(turno));
        }
        else{
            //cuando esta mal
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @GetMapping()
    public ResponseEntity<List<Turno>> buscarAllTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado=turnoService.buscar(id);
        if (turnoBuscado.isPresent()){
            //entra porque existe el turno con el id consultado
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else{
            //entra porque no existe un id en el listado de turnos
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se eliminó el turno con id="+id);
    }
    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;
        // Pedido - actualizar el turno id=1 con el paciente id=500 y odontologo id=1
        if (turnoService.buscar(turno.getId()).isPresent()){
            if (odontologoService.buscarOdontologoXId(turno.getOdontologo().getId()).isPresent()
                    &&pacienteService.buscar(turno.getPaciente().getId()).isPresent()){
                respuesta=ResponseEntity.ok(turnoService.actualizarTurno(turno));
            }
            else{
                respuesta=ResponseEntity.badRequest().build();
            }
        }
        else{
            respuesta=ResponseEntity.badRequest().build();
        }
        return respuesta;
    }

}
