package com.example.trabajofinalpettinaroli.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RECEIPT")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECEIPT_ID", columnDefinition = "integer(11)")
    private @NotNull Integer receiptId;

    @Column(name = "RECEIPT_DATE", columnDefinition = "datetime")
    private @NotNull Date date;

    @Column(name = "QUANTITY", columnDefinition = "integer(11)")
    private @NotNull Integer quantity;

    @Column(name = "TOTAL", columnDefinition = "float(5,2)")
    private @NotNull Float total;

    // Relación de la entidad Receipt con la entidad Cliente
    @ManyToOne(optional = false)
    @JoinColumn(name = "CLIENT_ID", columnDefinition = "integer(11)")
    private Client client;

    // Relación entre Receipt y ReceiptProducts
    @OneToMany(mappedBy = "receipt")
    private List<ReceiptProduct> lines;

    //CONSTRUCTORS

    public Receipt() {
    }

    public Date getDate() {
        return date;
    }

    // GETTERS & SETTERS


    public Integer getReceiptId() {
        return receiptId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptId=" + receiptId +
                ", date=" + date +
                ", quantity=" + quantity +
                ", total=" + total +
                ", client=" + client +
                ", lines=" + lines +
                '}';
    }
}
