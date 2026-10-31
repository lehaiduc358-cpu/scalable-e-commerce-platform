package com.vanesline.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wishlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wishlist {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "product_id")
    private Long productId;
}
