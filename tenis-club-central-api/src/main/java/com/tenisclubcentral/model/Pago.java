package com.tenisclubcentral.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

import com.tenisclubcentral.model.enums.EstadoPago;

import java.math.BigDecimal;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;
    
    @Column(name = "metodo_pago", nullable = false, length = 50)
    private String metodoPago;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPago estado;
    
    @Column(name = "numero_transaccion", length = 100)
    private String numeroTransaccion;
    
    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;
    
    @Column(columnDefinition = "TEXT")
    private String detalles;
    
    @Column(length = 255)
    private String comprobante;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscripcion_id", nullable = false)
    private Inscripcion inscripcion;
    
    @PrePersist
    public void prePersist() {
        this.fechaPago = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoPago.PENDIENTE;
        }
    }
}
