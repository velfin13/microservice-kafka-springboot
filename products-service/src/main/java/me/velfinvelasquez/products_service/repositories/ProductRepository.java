package me.velfinvelasquez.products_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.velfinvelasquez.products_service.models.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}