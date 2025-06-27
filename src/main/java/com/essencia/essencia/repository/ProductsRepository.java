package com.essencia.essencia.repository;

import com.essencia.essencia.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    Optional<Products> findByName(String name);

    List<Products> findByUser_Id(UUID userId);

}
