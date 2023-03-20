package com.project.finalprojectbackend;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String apellido;
    @Column
    private String nombre;
    @Column
    private String email;
    @Column
    private Integer dni;
//    @Column
//    private LocalDate fecha;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="domicilio_id",referencedColumnName = "id")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private Set<Turno> turnos= new HashSet<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }
//    public LocalDate getFecha() {
//        return fecha;
//    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
}
