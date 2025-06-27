package com.essencia.essencia.controller;


import com.essencia.essencia.controller.DTO.products.CreateProductsDTO;
import com.essencia.essencia.controller.DTO.products.ResponseProductsDTO;
import com.essencia.essencia.controller.DTO.products.PatchProductsDTO;
import com.essencia.essencia.controller.mappers.ProductsMapper;
import com.essencia.essencia.model.Products;
import com.essencia.essencia.service.ProductsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;
    private final ProductsMapper productsMapper;

    public ProductsController(ProductsService productsService, ProductsMapper productsMapper) {
        this.productsService = productsService;
        this.productsMapper = productsMapper;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody @Valid CreateProductsDTO createProductsDTO) {
        Products product = productsMapper.toEntity(createProductsDTO);
        productsService.save(product);
        return new ResponseEntity<Object>("Produto criado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResponseProductsDTO>> getProductsByUserId(@PathVariable("userId") String userId) {
        List<Products> products = productsService.retriveByUserId(UUID.fromString(userId));

        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ResponseProductsDTO> dtos = products.stream().map(productsMapper::toDTO).toList();

        return ResponseEntity.ok(dtos);
    }


    @GetMapping("{id}")
    public ResponseEntity<List<ResponseProductsDTO>> getProducts(@PathVariable("id") String id) {
        Optional<Products> products = productsService.retrive(Long.parseLong(id));

        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ResponseProductsDTO> dtos = products.stream().map(productsMapper::toDTO).toList();

        return ResponseEntity.ok(dtos);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id) {
        return productsService.retrive(Long.parseLong(id)).map(
                product -> {
                    productsService.delete(product);
                    return ResponseEntity.noContent().build();
                }
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody @Valid PatchProductsDTO patchProductsDTO) {
        productsService.patch(patchProductsDTO, Long.parseLong(id));
        return new ResponseEntity<Object>("Produto atualizado com sucesso!", HttpStatus.OK);
    }
}
