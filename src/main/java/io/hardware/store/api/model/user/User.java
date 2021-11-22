package io.hardware.store.api.model.user;

import io.hardware.store.api.model.order.Cart;
import io.hardware.store.api.model.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotEmpty
    private String userName;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private UserRoleType role;

    @OneToOne(targetEntity = Cart.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id_fk")
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<Order> orders;
}
