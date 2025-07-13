package com.tenisclubcentral.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

import com.tenisclubcentral.model.enums.EstadoInscripcion;

@Entity
@Table(name = "inscripcion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDateTime fechaInscripcion;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoInscripcion estado;
    
    @Column(name = "monto_abonado", precision = 10, scale = 2)
    private BigDecimal montoAbonado;
    
    @Column(name = "metodo_pago", length = 50)
    private String metodoPago;
    
    @Column(name = "numero_transaccion", length = 100)
    private String numeroTransaccion;
    
    @Column(columnDefinition = "TEXT")
    private String observaciones;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jugador_id", nullable = false)
    private Usuario jugador;
    
    @OneToMany(mappedBy = "inscripcion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pago> pagos;
    
    @PrePersist
    public void prePersist() {
        this.fechaInscripcion = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoInscripcion.PENDIENTE;
        }
    }
}
