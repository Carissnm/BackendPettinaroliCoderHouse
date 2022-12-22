package com.example.trabajofinalpettinaroli.services;

import com.example.trabajofinalpettinaroli.dto.ReceiptDTO;
import com.example.trabajofinalpettinaroli.dto.ReceiptProductDTO;
import com.example.trabajofinalpettinaroli.entities.Client;
import com.example.trabajofinalpettinaroli.entities.Product;
import com.example.trabajofinalpettinaroli.entities.Receipt;
import com.example.trabajofinalpettinaroli.entities.ReceiptProduct;
import com.example.trabajofinalpettinaroli.repositories.ClientRepository;
import com.example.trabajofinalpettinaroli.repositories.ProductRepository;
import com.example.trabajofinalpettinaroli.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class ReceiptService {


    ReceiptRepository receiptRepository;

    ClientRepository clientRepository;

    ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;

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

    // Method to build a Receipt
    private Receipt buildReceipt(Receipt receipt) {
        Receipt receiptToSave = new Receipt();

        receiptToSave.setClient(this.clientRepository.findById(receipt.getClient().getClientId()).get());

        LocalDate actualDate = this.restTemplate.getForObject("http://worldclockapi.com/api/json/utc/now", LocalDate.class);

        try {
            receiptToSave.setDate(actualDate);
        } catch (Exception e) {
            e.printStackTrace();
            receiptToSave.setDate(LocalDate.now()); // In case we can't obtain this information from the external API i obtain it from java's LocalDate.now()
        }

        receiptToSave.setLines(new HashSet<ReceiptProduct>());
        for(ReceiptProduct receiptProduct : receipt.getLines()) {
            receiptToSave.addLinea(createLine(receiptProduct));
        }

        receiptToSave.setTotal(calculateTotal(receiptToSave.getLines()));
        receiptToSave.setQuantity(receipt.getLines().size());

        return receiptToSave;
    }

    //Method that creates a lineDTO
    private Set<ReceiptProductDTO> createReceiptProductDTO(Set<ReceiptProduct> receiptProducts) {
        Set<ReceiptProductDTO> dtos = new HashSet<>();

        for(ReceiptProduct receiptProduct : receiptProducts) {
            ReceiptProductDTO dto = new ReceiptProductDTO();
            dto.setDescription(receiptProduct.getDescription());
            dto.setRpid(receiptProduct.getRpId());
            dto.setPrice(receiptProduct.getPrice());
            dto.setQuantity(receiptProduct.getQuantity());
            dtos.add(dto);
        }

        return dtos;
    }

    //Method to create a List of Receipts
    private List<ReceiptDTO> createReceiptsDTO(List<Receipt> receipts) {
        List<ReceiptDTO> receiptsDTOs = new ArrayList<>();
        for(Receipt receipt : receipts) {
            receiptsDTOs.add(this.createReceiptDTO(receipt));
        }

        return receiptsDTOs;
    }

    // Method to create a ReceiptDTO by getting a receipt as parameter
    private ReceiptDTO createReceiptDTO(Receipt receipt) {
        ReceiptDTO dto = new ReceiptDTO();

        dto.setReceiptId(receipt.getReceiptId());
        dto.setClient(receipt.getClient());
        dto.setDate(receipt.getDate());
        dto.setLines(createReceiptProductDTO(receipt.getLines()));

        return dto;
    }

    // Method to get a ReceiptDTO
    public ReceiptDTO findById(Long id) {
        Optional<Receipt> receipt = this.receiptRepository.findById(id);
        if(receipt.isPresent()) {
            return createReceiptDTO(receipt.get());
        } else {
            return new ReceiptDTO();
        }
    }

    // This method allows to create a line
    private ReceiptProduct createLine(ReceiptProduct receiptProduct) {
        ReceiptProduct lineToSave = new ReceiptProduct();

        Product productDB = this.productRepository.findById(receiptProduct.getProduct().getProductId()).get();

        lineToSave.setQuantity(receiptProduct.getQuantity());
        lineToSave.setDescription(receiptProduct.getDescription());
        lineToSave.setPrice(productDB.getPrice() * receiptProduct.getQuantity());
        lineToSave.setProduct(productDB);

        return lineToSave;
    }


    // Method to check if there's stock of a certain product.
    private Boolean isStock(Set<ReceiptProduct> receiptProducts){
        for(ReceiptProduct receiptProduct : receiptProducts) {
            Long productId = receiptProduct.getProduct().getProductId();
            Optional<Product> product = this.productRepository.findById(productId);

            if(product.isEmpty()) {
                return false;
            }
            // here we check that the quantity of items of a certain product is less or equals the amount
            // that's registered in our db.
            if(receiptProduct.getQuantity() <= product.get().getQuantity()) {
                return true;
            }
        }
        return false;
    }

    // Method to check if a certain product exists
    private Boolean isProduct(Set<ReceiptProduct> receiptProducts) {
        for ( ReceiptProduct receiptProduct : receiptProducts ) {
            Long productId = receiptProduct.getProduct().getProductId();
            Optional<Product> product = this.productRepository.findById(productId);

            if(product.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Method to check if a certain Client exists
    private Boolean isClient(Client client) {
        Optional<Client> c = this.clientRepository.findById(client.getClientId());

        return c.isPresent();
    }

    // The logic of this method calculates the total amount to pay for each Receipt
    // by adding the totals of each ReceiptProduct line.
    private Float calculateTotal(Set<ReceiptProduct> receiptProducts) {
        Float total = 0.00f;

        for(ReceiptProduct receiptProduct : receiptProducts) {
            total += receiptProduct.getPrice();
        }

        return total;
    }

}
