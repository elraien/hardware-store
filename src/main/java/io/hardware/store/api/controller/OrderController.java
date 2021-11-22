package io.hardware.store.api.controller;

import io.hardware.store.api.dto.OrderRequest;
import io.hardware.store.api.dto.OrderSummaryResponse;
import io.hardware.store.api.model.order.OrderItem;
import io.hardware.store.api.model.user.User;
import io.hardware.store.api.repository.CartRepository;
import io.hardware.store.api.repository.OrderItemRepository;
import io.hardware.store.api.repository.OrderSummaryRepository;
import io.hardware.store.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderItemRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderSummaryRepository orderSummaryRepository;

    @PostMapping("/orders")
    public User placeNewOrder(@RequestBody OrderRequest order) {
        return userRepository.save(order.getUser());
    }

    @PostMapping("/new-orderitem")
    public OrderItem addNewOrderItem(@RequestBody OrderItem order) {
        return orderRepository.save(order);
    }

    @GetMapping("/order-summary/{orderId}")
    public OrderSummaryResponse getSummary(@PathVariable Long orderId) {
        return orderSummaryRepository.findOrderSummaryInfo(orderId);
    }
}
