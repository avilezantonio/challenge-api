package com.agileengine.challenge.services;


import com.agileengine.challenge.dtos.ProductRequestDto;
import com.agileengine.challenge.entities.Client;
import com.agileengine.challenge.entities.Order;
import com.agileengine.challenge.entities.Product;
import com.agileengine.challenge.entities.User;
import com.agileengine.challenge.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(ProductServiceUnitTest.class);
    }

    @Test
    public void whenProductAdded_thenControlFlowAsExpected() {
        final ProductRequestDto productRequestDto = createProductRequestDTO();

        Mockito.when(modelMapper.map(productRequestDto, Product.class)).thenReturn(new Product());
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(new Product());

        productService.createProduct(productRequestDto);

        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
        Mockito.verify(modelMapper, Mockito.times(1)).map(productRequestDto, Product.class);
    }

    @Test
    public void whenProductUpdated_thenControlFlowAsExpected() {
        final ProductRequestDto productRequestDto = createProductRequestDTO();
        Mockito.when(productRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(new Product());
        Mockito.when(modelMapper.map(productRequestDto, Product.class)).thenReturn(new Product());

        productService.updateProduct(1L, productRequestDto);
        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
        Mockito.verify(productRepository, Mockito.times(1)).existsById(Mockito.anyLong());
        Mockito.verify(modelMapper, Mockito.times(1)).map(productRequestDto, Product.class);
    }

    @Test
    public void whenProductDeleted_thenControlFlowAsExpected() {
        Mockito.doNothing().when(productRepository).deleteById(Mockito.anyLong());
        productService.deleteProduct(1L);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
    @Test
    public void whenProductList_thenControlFlowAsExpected() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(new Product()));
        productService.getProducts();
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void whenProductFound_thenControlFlowAsExpected() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Product()));
        productService.getProduct(1L);
        Mockito.verify(productRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void whenProductsFound_thenControlFlowAsExpected() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(new Product()));
        productService.getProducts();
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void whenProductNotFound_thenFailsAsExpected() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> productService.getProduct(1L));
        Mockito.verify(productRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    private ProductRequestDto createProductRequestDTO(){
        final ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setName("Test");
        productRequestDto.setCode("A11");
        productRequestDto.setStock(10);
        productRequestDto.setPrice(BigDecimal.valueOf(15.5));

        return productRequestDto;
    }

}
