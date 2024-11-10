package com.agileengine.challenge.services;

import com.agileengine.challenge.dtos.OrderItemRequestDto;
import com.agileengine.challenge.dtos.OrderItemResponseDto;
import com.agileengine.challenge.entities.OrderItem;
import com.agileengine.challenge.repositories.OrderItemRepository;
import com.agileengine.challenge.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<OrderItemResponseDto> getOrderItems() {
        return orderItemRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order, OrderItemResponseDto.class))
                .toList();
    }

    public OrderItemResponseDto getOrderItem(Long id) {
        return modelMapper.map(findOrderItemById(id), OrderItemResponseDto.class);
    }

    public OrderItemResponseDto createOrderItem(OrderItemRequestDto orderItemDto) {
        final OrderItem item = modelMapper.map(orderItemDto, OrderItem.class);
        item.setCreatedAt(new Timestamp(new Date().getTime()));


        return modelMapper.map(orderItemRepository.save(item), OrderItemResponseDto.class);
    }

    public OrderItemResponseDto updateOrderItem(Long id, OrderItemRequestDto orderItemDto ) {
        final OrderItem orderItem = findOrderItemById(id);
        orderItem.setUpdatedAt(new Timestamp(new Date().getTime()));
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setQuantity(orderItemDto.getQuantity());

        return modelMapper.map(orderItemRepository.save(orderItem), OrderItemResponseDto.class);
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    private OrderItem findOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
