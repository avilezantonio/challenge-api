package com.agileengine.challenge.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class OrderItemRequestDto {

    @NotNull
    private Integer quantity;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Long orderId;
    @NotNull
    private Long productId;
}
