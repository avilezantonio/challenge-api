package com.agileengine.challenge.config;

import com.agileengine.challenge.dtos.OrderResponseDto;
import com.agileengine.challenge.dtos.OrderItemResponseDto;
import com.agileengine.challenge.dtos.ProductResponseDto;
import com.agileengine.challenge.entities.Order;
import com.agileengine.challenge.entities.OrderItem;
import com.agileengine.challenge.entities.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(skipProductFields);
        modelMapper.addMappings(skipOrderFields);
        modelMapper.addMappings(skipOrderItemFields);
        return modelMapper;
    }


    private final PropertyMap<ProductResponseDto, Product> skipProductFields = new PropertyMap<>() {
        protected void configure() {
            skip(destination.getId());
            skip(destination.getUpdatedAt());
            skip(destination.getCreatedAt());
        }
    };

    private final PropertyMap<OrderResponseDto, Order> skipOrderFields = new PropertyMap<>() {
        protected void configure() {
            skip(destination.getId());
            skip(destination.getUpdatedAt());
            skip(destination.getCreatedAt());
        }
    };

    private final PropertyMap<OrderItemResponseDto, OrderItem> skipOrderItemFields = new PropertyMap<>() {
        protected void configure() {
            skip(destination.getId());
            skip(destination.getUpdatedAt());
            skip(destination.getCreatedAt());
        }
    };
}
