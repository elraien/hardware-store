package io.hardware.store.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartResponse {

    private Long cartId;
    private Long productId;
    private Double productPrice;
    private int quantity;
    private Double totalOrderItemPrice;
}
