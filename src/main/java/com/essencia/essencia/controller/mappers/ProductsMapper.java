package com.essencia.essencia.controller.mappers;

import com.essencia.essencia.controller.DTO.products.CreateProductsDTO;
import com.essencia.essencia.controller.DTO.products.ResponseProductsDTO;
import com.essencia.essencia.controller.DTO.products.PatchProductsDTO;
import com.essencia.essencia.model.Products;
import com.essencia.essencia.repository.UsersRepository;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductsMapper {

    @Autowired
    UsersRepository usersRepository;

    @Mapping(target = "user", expression = "java(usersRepository.findById(dto.userId()).orElse(null))")

    public abstract Products toEntity(@Valid CreateProductsDTO dto);

    public abstract Products toEntity(@Valid PatchProductsDTO dto);

    public abstract ResponseProductsDTO toDTO(Products products);
}