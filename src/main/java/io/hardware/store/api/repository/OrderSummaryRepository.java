package io.hardware.store.api.repository;

import io.hardware.store.api.dto.OrderSummaryResponse;
import io.hardware.store.api.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderSummaryRepository extends JpaRepository<Order, Long> {

    @Query("SELECT new io.hardware.store.api.dto.OrderSummaryResponse(o.orderId, o.orderDate, c.totalSum) FROM Order o JOIN o.cart c WHERE o.orderId =:orderId")
    OrderSummaryResponse findOrderSummaryInfo(Long orderId);
}
