package com.essencia.essencia.validator;

import com.essencia.essencia.exceptions.DuplicatedRecordException;
import com.essencia.essencia.model.Products;
import com.essencia.essencia.repository.ProductsRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductsValidator {
    private final ProductsRepository productsRepository;

    public ProductsValidator(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public void validator(Products products) {

        if (productsExists(products)) {
            throw new DuplicatedRecordException("Produto já cadastrado");
        }
    }

    public boolean productsExists(Products products) {
        Optional<Products> productsFound = productsRepository.findByName(products.getName());


        return productsFound
                .filter(p -> products.getId() == null || !products.getId().equals(p.getId()))
                .isPresent();
    }
}
