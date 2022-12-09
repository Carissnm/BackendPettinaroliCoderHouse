package com.example.trabajofinalpettinaroli.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "RECEIPT_PRODUCT")
public class ReceiptProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RP_ID", columnDefinition = "integer(11)")
    private @NotNull Integer rpId;

    @Column(name = "RP_DESCRIPTION", columnDefinition = "varchar(255)")
    private @NotNull String description;

    @Column(name = "RP_QUANTITY", columnDefinition = "integer(11)")
    private @NotNull Integer quantity;

    @Column(name = "PRICE", columnDefinition = "float(5,2)")
    private @NotNull Float price;

    @ManyToOne
    @JoinColumn(name = "RECEIPT_ID", columnDefinition = "integer(11)")
    Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", columnDefinition = "integer(11)")
    Product product;

    public ReceiptProduct() {
    }

    public ReceiptProduct(String description, Integer quantity, Float price) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    // GETTERS & SETTERS


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRpId() {
        return rpId;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ReceiptProduct{" +
                "rpId=" + rpId +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price=" + price +
                ", receipt=" + receipt +
                ", product=" + product +
                '}';
    }
}
