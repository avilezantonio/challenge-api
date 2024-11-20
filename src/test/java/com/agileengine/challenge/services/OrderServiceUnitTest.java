package com.agileengine.challenge.services;

import com.agileengine.challenge.dtos.OrderRequestDto;
import com.agileengine.challenge.dtos.OrderResponseDto;
import com.agileengine.challenge.entities.*;
import com.agileengine.challenge.repositories.ClientRepository;
import com.agileengine.challenge.repositories.OrderRepository;
import com.agileengine.challenge.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void whenOrderAdded_thenControlFlowAsExpected() {
        final OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setClientId(1L);
        orderRequestDto.setUserId(1L);

        Mockito.when(modelMapper.map(orderRequestDto, Order.class)).thenReturn(new Order());
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(new Order());
        Mockito.when(clientRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Client()));
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new User()));

        orderService.createOrder(orderRequestDto);

        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(Order.class));
        Mockito.verify(modelMapper, Mockito.times(1)).map(orderRequestDto, Order.class);
        Mockito.verify(clientRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void whenOrderUpdated_thenControlFlowAsExpected() {
        final OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setStatus(Status.COMPLETED);


        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Order()));
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(new Order());

        orderService.updateOrder(1L, orderRequestDto);

        Mockito.verify(orderRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(Order.class));
    }

    @Test
    public void whenOrderDeleted_thenControlFlowAsExpected() {
        Mockito.doNothing().when(orderRepository).deleteById(Mockito.anyLong());
        orderService.deleteOrder(1L);
        Mockito.verify(orderRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    public void whenOrderFound_thenControlFlowAsExpected() {
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Order()));
        orderService.getOrder(1L);
        Mockito.verify(orderRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void whenOrdersFound_thenControlFlowAsExpected() {
        final Order order = new Order();
        Mockito.when(orderRepository.findAll()).thenReturn(List.of(order));
        Mockito.when(modelMapper.map(order, OrderResponseDto.class)).thenReturn(new OrderResponseDto());
        orderService.getOrders();
        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
        Mockito.verify(modelMapper, Mockito.times(1)).map(order, OrderResponseDto.class);
    }

    @Test
    public void whenOrderNotFound_thenControlFailsAsExpected() {
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> orderService.getOrder(1L));
        Mockito.verify(orderRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

}
