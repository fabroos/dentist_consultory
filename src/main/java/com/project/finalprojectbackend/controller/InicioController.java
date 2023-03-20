package com.project.finalprojectbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
public class InicioController {
    /*
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public InicioController(PacienteService pacienteService, OdontologoService odontologoService) {
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public String mostrarDatosDePrueba (
            Model model, @RequestParam("email") String email,
            @RequestParam("id") Integer id){
        Paciente paciente=pacienteService.buscarXEmail(email);
        Odontologo odontologo=odontologoService.buscarOdontologoXId(id);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        model.addAttribute("matricula",odontologo.getMatricula());
        return "index";
    }

     */
}
