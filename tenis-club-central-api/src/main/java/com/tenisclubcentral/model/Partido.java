package com.tenisclubcentral.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

import com.tenisclubcentral.model.enums.EstadoPartido;
import com.tenisclubcentral.model.enums.FasePartido;

@Entity
@Table(name = "partido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FasePartido fase;
    
    @Column(name = "numero_ronda", nullable = false)
    private Integer numeroRonda;
    
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;
    
    @Column(length = 50)
    private String cancha;
    
    @Column(length = 20)
    private String resultado;
    
    @Column(name = "set_jugador1")
    private Integer setJugador1;
    
    @Column(name = "set_jugador2")
    private Integer setJugador2; //TODO reveer por dobles
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPartido estado;
    
    @Column(columnDefinition = "TEXT")
    private String observaciones;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jugador1_id", nullable = false)
    private Usuario jugador1;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jugador2_id", nullable = false)
    private Usuario jugador2;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ganador_id")
    private Usuario ganador;
    
    @PrePersist
    public void prePersist() {
        if (this.estado == null) {
            this.estado = EstadoPartido.PROGRAMADO;
        }
    }
}
