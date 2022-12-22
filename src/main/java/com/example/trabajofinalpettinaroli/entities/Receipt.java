package com.example.trabajofinalpettinaroli.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "RECEIPT")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECEIPT_ID", columnDefinition = "integer(11)")
    private @NotNull Long id;

    @Column(name = "RECEIPT_DATE", columnDefinition = "datetime")
    private @NotNull LocalDate date;

    @Column(name = "QUANTITY", columnDefinition = "integer(11)")
    private @NotNull Integer quantity;

    @Column(name = "TOTAL", columnDefinition = "float(10,2)")
    private @NotNull Float total;

    // Relación de la entidad Receipt con la entidad Cliente
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", columnDefinition = "integer(11)")
    private Client client;

    // Relación entre Receipt y ReceiptProducts
    @OneToMany(mappedBy = "receipt")
    private Set<ReceiptProduct> lines;

    //CONSTRUCTORS

    public Receipt() {
    }

    public LocalDate getDate() {
        return date;
    }

    // GETTERS & SETTERS


    public Set<ReceiptProduct> getLines() {
        return lines;
    }

    public void setLines(Set<ReceiptProduct> lines) {
        this.lines = lines;
    }

    public Long getReceiptId() {
        return id;
    }

    public void setDate(LocalDate date) {
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

    public ReceiptProduct addLinea(ReceiptProduct linea) {
        getLines().add(linea);
        linea.setReceipt(this);

        return linea;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptId=" + id +
                ", date=" + date +
                ", quantity=" + quantity +
                ", total=" + total +
                ", client=" + client +
                ", lines=" + lines +
                '}';
    }
}
