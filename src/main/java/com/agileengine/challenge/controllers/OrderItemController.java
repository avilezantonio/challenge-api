package com.agileengine.challenge.controllers;

import com.agileengine.challenge.dtos.OrderItemRequestDto;
import com.agileengine.challenge.dtos.OrderItemResponseDto;
import com.agileengine.challenge.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemResponseDto>> getOrderItems() {
        return ResponseEntity.ok(orderItemService.getOrderItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponseDto> getOrderItem(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getOrderItem(id));
    }

    @PostMapping
    public ResponseEntity<OrderItemResponseDto> createOrderItem(@RequestBody OrderItemRequestDto orderItem) {
        return ResponseEntity.ok(orderItemService.createOrderItem(orderItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemResponseDto> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemRequestDto orderItemDto) {
        return ResponseEntity.ok(orderItemService.updateOrderItem(id, orderItemDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);

        return ResponseEntity.ok().build();
    }

}
