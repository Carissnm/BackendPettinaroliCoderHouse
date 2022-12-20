package com.example.trabajofinalpettinaroli.repositories;

import com.example.trabajofinalpettinaroli.entities.ReceiptProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptProductRepository extends JpaRepository<ReceiptProduct, Long> {
}
