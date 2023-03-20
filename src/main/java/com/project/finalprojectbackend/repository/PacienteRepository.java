package com.project.finalprojectbackend.repository;

import com.example.clase22.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    @Query("select p from Paciente p where p.email=?1")
    Optional<Paciente> buscarPorCorreo(String email);
}
