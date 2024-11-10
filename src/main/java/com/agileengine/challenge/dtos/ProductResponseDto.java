package com.agileengine.challenge.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class ProductResponseDto extends BaseResponseDto {

    private String code;
    private String name;
    private BigDecimal price;
    private Integer stock;
}
