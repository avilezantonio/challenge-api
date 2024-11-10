package com.agileengine.challenge.services;

import com.agileengine.challenge.dtos.ProductRequestDto;
import com.agileengine.challenge.dtos.ProductResponseDto;
import com.agileengine.challenge.entities.Product;
import com.agileengine.challenge.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    //TODO: add pagination here
    public List<ProductResponseDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .toList();
    }

    public ProductResponseDto getProduct(Long id) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(product, ProductResponseDto.class);
    }

    public ProductResponseDto createProduct(ProductRequestDto productDto) {
        final Product product = modelMapper.map(productDto, Product.class);
        product.setCreatedAt(getCurrentTimestamp());

        return modelMapper.map(productRepository.save(product), ProductResponseDto.class);
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productDto ) {
        if(productRepository.existsById(id)) {
            final Product product = modelMapper.map(productDto, Product.class);
            product.setId(id);
            product.setUpdatedAt(getCurrentTimestamp());

            return modelMapper.map(productRepository.save(product), ProductResponseDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //Making the delete idempotent by not returning error when the product not exists
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Map<Long, BigDecimal> getProductsPriceMap(List<Long> products){
        return productRepository.findAllById(products)
                .stream()
                .collect(Collectors.toMap(Product::getId, Product::getPrice));
    }

    private Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }
}
