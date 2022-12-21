package com.example.trabajofinalpettinaroli.repositories;

import com.example.trabajofinalpettinaroli.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    public Optional<Client> findClientByDni(String dni);
}
