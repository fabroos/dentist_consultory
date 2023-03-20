package com.project.finalprojectbackend.service;

import com.example.clase22.entities.Odontologo;
import com.example.clase22.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Optional<Odontologo> buscarOdontologoXId(Long id){
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> buscarTodosOdontologos(){
        return odontologoRepository.findAll();
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public Odontologo actualizarOdontologo(Odontologo odontologo){
        //implementar un control de ID
        return odontologoRepository.save(odontologo);
    }
    public void borrarOdontologo(Long id){
        odontologoRepository.deleteById(id);
    }

}
