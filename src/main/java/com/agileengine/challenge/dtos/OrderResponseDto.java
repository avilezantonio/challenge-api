package com.agileengine.challenge.dtos;

import com.agileengine.challenge.entities.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OrderResponseDto extends BaseResponseDto {

    private List<OrderItemResponseDto> items = List.of();
    private Status status = Status.DRAFT;

}
