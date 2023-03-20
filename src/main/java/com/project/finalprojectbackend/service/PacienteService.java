package com.project.finalprojectbackend.service;

import com.example.clase22.entities.Paciente;
import com.example.clase22.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardar(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscar(Long id){
        return pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarXEmail(String email){
        return pacienteRepository.buscarPorCorreo(email);
    }
    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }
    public Paciente actualizar(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
}
