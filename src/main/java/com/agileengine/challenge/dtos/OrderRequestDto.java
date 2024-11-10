package com.agileengine.challenge.dtos;

import com.agileengine.challenge.entities.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto extends BaseResponseDto {

    private Status status = Status.DRAFT;
    private Long userId;
    private Long clientId;
    private List<OrderItemResponseDto> items = List.of();

}

