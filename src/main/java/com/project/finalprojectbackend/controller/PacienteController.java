package com.project.finalprojectbackend.controller;

import com.example.clase22.entities.Paciente;
import com.example.clase22.servicio.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardar(paciente);
    }
    @GetMapping
    public List<Paciente> buscarAllPacientes (){
        return pacienteService.buscarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado=pacienteService.buscar(id);
        if (pacienteBuscado.isPresent()){
            System.out.println(pacienteBuscado.get());
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping
    public ResponseEntity<Paciente> buscarPaciente(@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscar(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            Paciente pacienteActualizado = pacienteService.actualizar(paciente);
            return ResponseEntity.ok(pacienteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
