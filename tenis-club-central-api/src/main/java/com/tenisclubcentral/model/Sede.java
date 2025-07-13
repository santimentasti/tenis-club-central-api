package com.tenisclubcentral.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sede")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sede {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @Column(nullable = false)
    private String direccion;
    
    @Column(nullable = false, length = 100)
    private String ciudad;
    
    @Column(nullable = false, length = 100)
    private String provincia;
    
    @Column(name = "codigo_postal", length = 10)
    private String codigoPostal;
    
    @Column(nullable = false)
    private Double latitud;
    
    @Column(nullable = false)
    private Double longitud;
    
    @Column(length = 20)
    private String telefono;
    
    @Column(length = 255)
    private String email;
    
    @Column(name = "capacidad_canchas")
    private Integer capacidadCanchas;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(nullable = false)
    private Boolean activa = true;
    
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizador_id", nullable = false)
    private Usuario organizador;
    
    @OneToMany(mappedBy = "sede", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Torneo> torneos;
    
    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
