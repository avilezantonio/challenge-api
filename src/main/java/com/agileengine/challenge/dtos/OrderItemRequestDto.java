package com.agileengine.challenge.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class OrderItemRequestDto {

    private Integer quantity;
    private BigDecimal price;
    private Long orderId;
    private Long productId;
}
