package com.agileengine.challenge.services;

import com.agileengine.challenge.dtos.OrderItemRequestDto;
import com.agileengine.challenge.dtos.OrderRequestDto;
import com.agileengine.challenge.dtos.OrderResponseDto;
import com.agileengine.challenge.entities.*;
import com.agileengine.challenge.repositories.ClientRepository;
import com.agileengine.challenge.repositories.OrderRepository;
import com.agileengine.challenge.repositories.UserRepository;
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
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public List<OrderResponseDto> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order, OrderResponseDto.class))
                .toList();
    }

    public OrderResponseDto getOrder(Long id) {
        return modelMapper.map(findOrderById(id), OrderResponseDto.class);
    }

    public OrderResponseDto createOrder(OrderRequestDto orderDto) {
        final Order order = modelMapper.map(orderDto, Order.class);
        order.setCreatedAt(new Timestamp(new Date().getTime()));
        order.setUser(userRepository.findById(orderDto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        order.setClient(clientRepository.findById(orderDto.getClientId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        if(orderDto.getItems() != null && !orderDto.getItems().isEmpty()) {
            order.setOrderItems(buildItems(order, orderDto.getItems()));
        }

        return modelMapper.map(orderRepository.save(order), OrderResponseDto.class);
    }


    public OrderResponseDto updateOrder(Long id, OrderRequestDto orderDto) {
        final Order order = findOrderById(id);
        order.setUpdatedAt(getCurrentTimestamp());
        order.setStatus(orderDto.getStatus());

        return modelMapper.map(orderRepository.save(order), OrderResponseDto.class);
    }

    //Making the delete idempotent by not returning error when the order not exists
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }


    private Order findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private List<OrderItem> buildItems(Order order, List<OrderItemRequestDto> items) {
        return items.stream()
                .map(item -> {
                    final OrderItem orderItem = modelMapper.map(item, OrderItem.class);
                    orderItem.setOrder(order);
                    orderItem.setCreatedAt(order.getCreatedAt());
                    return orderItem;
                })
                .toList();
    }

    private Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }
}
