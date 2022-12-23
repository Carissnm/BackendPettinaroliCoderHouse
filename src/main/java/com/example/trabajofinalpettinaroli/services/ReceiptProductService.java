package com.example.trabajofinalpettinaroli.services;


import com.example.trabajofinalpettinaroli.repositories.ProductRepository;
import com.example.trabajofinalpettinaroli.repositories.ReceiptProductRepository;
import com.example.trabajofinalpettinaroli.repositories.ReceiptRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReceiptProductService {

    ReceiptProductRepository repository;

    ProductRepository prdRepository;

    ReceiptRepository receiptRepository;


}
