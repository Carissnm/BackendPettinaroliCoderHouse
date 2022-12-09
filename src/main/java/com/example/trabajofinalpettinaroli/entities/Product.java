package com.example.trabajofinalpettinaroli.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PRODUCT_ID", columnDefinition = "integer(11)")
   private @NotNull Integer productId;

   @Column(name = "PRODUCT_CODE", columnDefinition = "integer(11)", unique = true)
   private @NotNull Integer productCode;

   @Column(name = "PRD_DESCRIPTION", columnDefinition = "varchar(255)")
   private @NotNull String description;

   @Column(name = "PRD_QUANTITY", columnDefinition = "integer(11)")
   private @NotNull Integer quantity;

   @Column(name = "PRD_PRICE", columnDefinition = "float(5,2)")
   private Float price;

   // mapeo la tabla ReceiptProduct desde producto para generar la relación OneToMany
   @OneToMany(mappedBy = "product")
   private List<ReceiptProduct> lines;

   //CONSTRCUTORS
   public Product() {
   }

   public Product(Integer productCode, String description, Integer quantity, Float price) {
      this.productCode = productCode;
      this.description = description;
      this.quantity = quantity;
      this.price = price;
   }

   // GETTERS & SETTERS

   public Integer getProductId() {
      return productId;
   }

   public void setProductId(Integer productId) {
      this.productId = productId;
   }

   public Integer getProductCode() {
      return productCode;
   }

   public void setProductCode(Integer productCode) {
      this.productCode = productCode;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
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

   public List<ReceiptProduct> getLines() {
      return lines;
   }

   public void setLines(List<ReceiptProduct> lines) {
      this.lines = lines;
   }

   @Override
   public String toString() {
      return "Product{" +
              "productId=" + productId +
              ", productCode=" + productCode +
              ", description='" + description + '\'' +
              ", quantity=" + quantity +
              ", price=" + price +
              '}';
   }
}
