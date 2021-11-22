package io.hardware.store.api.repository;

import io.hardware.store.api.dto.CartResponse;
import io.hardware.store.api.model.order.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT new io.hardware.store.api.dto.CartResponse(c.cartId, o.productId, o.productPrice, o.quantity, o.totalOrderItemPrice) FROM Cart c JOIN c.items o WHERE c.cartId =:cartId")
    List<CartResponse> findCartWithItems(Long cartId);
}
