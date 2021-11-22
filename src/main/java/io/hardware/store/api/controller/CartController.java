package io.hardware.store.api.controller;

import io.hardware.store.api.dto.CartResponse;
import io.hardware.store.api.model.order.Cart;
import io.hardware.store.api.repository.CartRepository;
import io.hardware.store.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/carts/{userId}")
    Cart createCart(@PathVariable Long userId, @RequestBody Cart cart) {
        return cartService.createNewCart(userId, cart);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/carts")
    Cart addNewItem(@RequestBody Cart request) {
        return cartRepository.save(request);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cart/{cartId}")
    List<CartResponse> findCart(@PathVariable Long cartId) {
        return cartRepository.findCartWithItems(cartId);
    }

}
