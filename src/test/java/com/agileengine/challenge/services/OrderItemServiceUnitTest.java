package com.agileengine.challenge.services;

import com.agileengine.challenge.dtos.OrderItemRequestDto;
import com.agileengine.challenge.dtos.OrderItemResponseDto;
import com.agileengine.challenge.entities.*;
import com.agileengine.challenge.repositories.OrderItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class OrderItemServiceUnitTest {
    @InjectMocks
    private OrderItemService orderItemService;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void whenOrderItemAdded_thenControlFlowAsExpected() {
        final OrderItemRequestDto orderItemRequestDto = new OrderItemRequestDto();
        orderItemRequestDto.setProductId(1L);
        orderItemRequestDto.setQuantity(1);
        orderItemRequestDto.setOrderId(1L);
        orderItemRequestDto.setPrice(BigDecimal.TEN);

        Mockito.when(modelMapper.map(orderItemRequestDto, OrderItem.class)).thenReturn(new OrderItem());
        Mockito.when(orderItemRepository.save(Mockito.any(OrderItem.class))).thenReturn(new OrderItem());

        orderItemService.createOrderItem(orderItemRequestDto);

        Mockito.verify(orderItemRepository, Mockito.times(1)).save(Mockito.any(OrderItem.class));
        Mockito.verify(modelMapper, Mockito.times(1)).map(orderItemRequestDto, Order.class);
    }

    @Test
    public void whenOrderItemUpdated_thenControlFlowAsExpected() {
        final OrderItemRequestDto orderItemRequestDto = new OrderItemRequestDto();
        orderItemRequestDto.setQuantity(5);

        Mockito.when(orderItemRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new OrderItem()));
        Mockito.when(orderItemRepository.save(Mockito.any(OrderItem.class))).thenReturn(new OrderItem());
        orderItemService.updateOrderItem(1L, orderItemRequestDto);
        Mockito.verify(orderItemRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(orderItemRepository, Mockito.times(1)).save(Mockito.any(OrderItem.class));
    }

    @Test
    public void whenOrderItemDeleted_thenControlFlowAsExpected() {
        Mockito.doNothing().when(orderItemRepository).deleteById(Mockito.anyLong());
        orderItemService.deleteOrderItem(1L);
        Mockito.verify(orderItemRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    public void whenOrderItemFound_thenControlFlowAsExpected() {
        Mockito.when(orderItemRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new OrderItem()));
        orderItemService.getOrderItem(1L);
        Mockito.verify(orderItemRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void whenOrderItemsFound_thenControlFlowAsExpected() {
        final OrderItem orderItem = new OrderItem();

        Mockito.when(orderItemRepository.findAll()).thenReturn(List.of(orderItem));
        Mockito.when(modelMapper.map(orderItem, OrderItemResponseDto.class)).thenReturn(new OrderItemResponseDto());
        orderItemService.getOrderItems();
        Mockito.verify(orderItemRepository, Mockito.times(1)).findAll();
        Mockito.verify(modelMapper, Mockito.times(1)).map(orderItem, OrderItemResponseDto.class);
    }

    @Test
    public void whenOrderItemNotFound_thenControlFailsAsExpected() {
        Mockito.when(orderItemRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> orderItemService.getOrderItem(1L));
        Mockito.verify(orderItemRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

}
