package io.hardware.store.api.service;

import io.hardware.store.api.model.order.Cart;
import io.hardware.store.api.model.user.UserRoleType;
import io.hardware.store.api.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CartService {

    Logger logger = Logger.getLogger(CartService.class.getName());

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    public Cart createNewCart(Long userId, Cart cart) {
        boolean hasExpectedRole = userService.checkUserRole(userId, UserRoleType.CUSTOMER);
        if (hasExpectedRole) {
            return cartRepository.save(cart);
        }
        logger.info(String.format("User with id: %s is not a Customer.", userId));
        return null;
    }
}
