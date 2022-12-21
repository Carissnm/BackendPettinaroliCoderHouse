package com.example.trabajofinalpettinaroli.services;

import com.example.trabajofinalpettinaroli.entities.Receipt;
import com.example.trabajofinalpettinaroli.repositories.ClientRepository;
import com.example.trabajofinalpettinaroli.repositories.ProductRepository;
import com.example.trabajofinalpettinaroli.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReceiptService {


    ReceiptRepository receiptRepository;

    ClientRepository clientRepository;

    ProductRepository productRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository, ClientRepository clientRepository, ProductRepository productRepository) {
        this.receiptRepository = receiptRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Optional<Receipt> getReceiptById(Long id) {
        return receiptRepository.findById(id);
    }

}
