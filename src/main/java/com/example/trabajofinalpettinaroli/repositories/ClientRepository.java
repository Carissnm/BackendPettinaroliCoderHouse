package com.example.trabajofinalpettinaroli.repositories;

import com.example.trabajofinalpettinaroli.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Creo este método para utilizarlo luego en Service para chequear si un cliente ya existe por su Dni.
    public Optional<Client> findClientByDni(String dni);
}
