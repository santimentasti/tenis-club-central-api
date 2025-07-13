package com.tenisclubcentral.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

import com.tenisclubcentral.model.enums.RolUsuario;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 100)
    private String apellido;
    
    @Column(unique = true, nullable = false, length = 255)
    private String email;
    
    @Column(length = 20)
    private String telefono;
    
    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolUsuario rol;
    
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    // Relaciones
    @OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sede> sedes;
    
    @OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Torneo> torneosOrganizados;
    
    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones;
    
    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}

