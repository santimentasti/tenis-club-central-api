package com.tenisclubcentral.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

import com.tenisclubcentral.model.enums.Categoria;
import com.tenisclubcentral.model.enums.EstadoTorneo;
import com.tenisclubcentral.model.enums.Modalidad;
import com.tenisclubcentral.model.enums.TipoTorneo;


@Entity
@Table(name = "torneo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Torneo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;
    
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;
    
    @Column(name = "fecha_inicio_inscripcion", nullable = false)
    private LocalDateTime fechaInicioInscripcion;
    
    @Column(name = "fecha_fin_inscripcion", nullable = false)
    private LocalDateTime fechaFinInscripcion;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_torneo", nullable = false)
    private TipoTorneo tipoTorneo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Modalidad modalidad;
    
    @Column(name = "max_participantes", nullable = false)
    private Integer maxParticipantes;
    
    @Column(name = "costo_inscripcion", precision = 10, scale = 2)
    private BigDecimal costoInscripcion;
    
    @Column(name = "premio_ganador", precision = 10, scale = 2)
    private BigDecimal premioGanador;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTorneo estado;
    
    @Column(columnDefinition = "TEXT")
    private String reglamento;
    
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sede_id", nullable = false)
    private Sede sede;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizador_id", nullable = false)
    private Usuario organizador;
    
    @OneToMany(mappedBy = "torneo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones;
    
    @OneToMany(mappedBy = "torneo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Partido> partidos;
    
    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoTorneo.PROGRAMADO;
        }
    }
}
