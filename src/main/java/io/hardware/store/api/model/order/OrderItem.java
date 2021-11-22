package io.hardware.store.api.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_items")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id_fk")
    private Long productId;

    private int quantity;
    private Double productPrice;
    private Double totalOrderItemPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id_fk", referencedColumnName = "cart_id")
    private Cart cart;
}
