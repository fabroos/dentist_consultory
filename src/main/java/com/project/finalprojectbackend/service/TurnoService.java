package com.project.finalprojectbackend.service;

import com.example.clase22.dto.TurnoDTO;
import com.example.clase22.entities.Odontologo;
import com.example.clase22.entities.Paciente;
import com.example.clase22.entities.Turno;
import com.example.clase22.exceptions.ResourceNotFoundException;
import com.example.clase22.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;
    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public TurnoDTO guardar(TurnoDTO turno){
        //yo necesito una entidad para guardar
        //yo tengo un DTO complejo y necesito pasarlo a una entidad
        //yo ahora tengo que devolver un DTO, yo recibía un Turno y no un DTO
        Turno turnoEntity= new Turno();
        turnoEntity.setFecha(turno.getFecha());

        Paciente paciente= new Paciente();
        paciente.setId(turno.getPaciente_id());
        Odontologo odontologo= new Odontologo();
        odontologo.setId(turno.getOdontologo_id());

        turnoEntity.setPaciente(paciente);
        turnoEntity.setOdontologo(odontologo);

        Turno turnoGuardado=turnoRepository.save(turnoEntity);
        //alternativa A
        TurnoDTO turnoADevolver= new TurnoDTO();
        turnoADevolver.setFecha(turnoGuardado.getFecha());
        turnoADevolver.setOdontologo_id(turnoGuardado.getOdontologo().getId());
        turnoADevolver.setPaciente_id(turnoGuardado.getPaciente().getId());
        turnoADevolver.setId(turnoGuardado.getId());

        //alternativa B
        //turno.setId(turnoGuardado.getId());
        //return turno;
        return turnoADevolver;
    }
    public Optional<TurnoDTO> buscar(Long id){
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        Optional<TurnoDTO> turnoDTOBuscado=Optional.empty();
        if (turnoBuscado.isPresent()){
            Turno turnoRealBuscado=turnoBuscado.get();
            TurnoDTO turnoDTOADevolver= new TurnoDTO();
            turnoDTOADevolver.setId(turnoRealBuscado.getId());
            turnoDTOADevolver.setFecha(turnoRealBuscado.getFecha());
            turnoDTOADevolver.setPaciente_id(turnoRealBuscado.getPaciente().getId());
            turnoDTOADevolver.setOdontologo_id(turnoRealBuscado.getOdontologo().getId());
            turnoDTOBuscado=Optional.of(turnoDTOADevolver);
        }
        return turnoDTOBuscado;
    }
    public List<Turno> buscarTodos(){
        return turnoRepository.findAll();
    }
    public Turno actualizarTurno(Turno turno){
        return turnoRepository.save(turno);
    }
    public void eliminarTurno(Long id) throws ResourceNotFoundException{
        // si no existe el id del turno ingresado, lanzar la execepción.
        Optional<TurnoDTO> turnoAEliminar= buscar(id);
        if (turnoAEliminar.isPresent()){
            turnoRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("No existe el id del turno ingresado - id="+id);
        }

    }
}
