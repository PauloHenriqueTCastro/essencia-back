package com.essencia.essencia.service;

import com.essencia.essencia.controller.DTO.products.PatchProductsDTO;
import com.essencia.essencia.model.Products;
import com.essencia.essencia.repository.ProductsRepository;
import com.essencia.essencia.validator.ProductsValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final ProductsValidator productsValidator;


    public ProductsService(ProductsRepository productsRepository, ProductsValidator productsValidator) {
        this.productsRepository = productsRepository;
        this.productsValidator = productsValidator;
    }

    public void save(Products products) {
        productsValidator.validator(products);
        productsRepository.save(products);
    }

    public List<Products> retriveByUserId(UUID userId) {
        return productsRepository.findByUser_Id(userId);
    }

    public Optional<Products> retrive(Long id) {
        return productsRepository.findById(id);
    }

    public void delete(Products products) {
        productsRepository.delete(products);
    }

    public void patch(PatchProductsDTO patchProductsDTO, Long id) {
        Products existing = productsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        if (patchProductsDTO.name() != null) existing.setName(patchProductsDTO.name());
        if (patchProductsDTO.price() != null) existing.setPrice(patchProductsDTO.price());
        if (patchProductsDTO.quantity() != null) existing.setQuantity(patchProductsDTO.quantity());
        if (patchProductsDTO.productUrl() != null) existing.setProductUrl(patchProductsDTO.productUrl());

        productsValidator.validator(existing);
        productsRepository.save(existing);
    }

}
