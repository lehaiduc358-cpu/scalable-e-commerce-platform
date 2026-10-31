package com.vanesline.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "amount", precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(name = "method")
    private String method;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
