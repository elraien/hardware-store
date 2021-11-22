package io.hardware.store.api.model.order;

import io.hardware.store.api.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    private Double totalSum;

    @OneToMany(mappedBy = "cart", cascade=CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();
}
