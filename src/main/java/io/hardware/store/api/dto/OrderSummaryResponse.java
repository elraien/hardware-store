package io.hardware.store.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderSummaryResponse {
    private Long orderId;
    private LocalDateTime orderDate;
    private Double totalSum;
}
