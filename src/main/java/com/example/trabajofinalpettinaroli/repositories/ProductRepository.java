package com.example.trabajofinalpettinaroli.repositories;

import com.example.trabajofinalpettinaroli.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Creo este método para utilizarlo luego en Service para chequear si un producto ya existe por su Código.
    public Optional<Product> findProductByProductCode(Integer productCode);
}
