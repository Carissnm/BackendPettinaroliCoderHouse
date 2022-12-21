package com.example.trabajofinalpettinaroli.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID", columnDefinition = "integer(11)")
    private @NotNull Long id;

    @Column(name = "CLIENT_DNI", columnDefinition = "varchar(255)")
    private @NotNull String dni;

    @Column(name = "CLIENT_NAME", columnDefinition = "varchar(255)")
    private @NotNull String clientName;

    @Column(name = "CLIENT_SURNAME", columnDefinition = "varchar(255)")
    private @NotNull String clientSurname;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Receipt> receipts;


    // CONSTRUCTORS
    public Client() {
    }

    public Client(String dni, String clientName, String clientSurname) {
        this.dni = dni;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.receipts = new ArrayList<>();
    }

    //GETTERS & SETTERS

    public Long getClientId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + id +
                ", dni='" + dni + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                ", receipts=" + receipts +
                '}';
    }
}
