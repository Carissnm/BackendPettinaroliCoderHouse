package com.example.trabajofinalpettinaroli.dto;

import com.example.trabajofinalpettinaroli.entities.Client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class ReceiptDTO {

    private Long receiptId;
    private Integer quantity;
    private LocalDate date;
    private BigDecimal total;
    private Client client;
    private Set<ReceiptProductDTO> lines;

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<ReceiptProductDTO> getLines() {
        return lines;
    }

    public void setLines(Set<ReceiptProductDTO> lines) {
        this.lines = lines;
    }
}
