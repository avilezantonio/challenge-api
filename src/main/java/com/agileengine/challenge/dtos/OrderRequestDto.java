package com.agileengine.challenge.dtos;

import com.agileengine.challenge.entities.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {

    @NotNull
    private Long userId;
    @NotNull
    private Long clientId;
    private Status status = Status.DRAFT;
    private List<OrderItemRequestDto> items = List.of();

}

