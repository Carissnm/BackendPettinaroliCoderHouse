package com.example.trabajofinalpettinaroli.services;

import com.example.trabajofinalpettinaroli.entities.Client;
import com.example.trabajofinalpettinaroli.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService  {



    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    // Create Client Method:
    public Client createClient(Client client) {
       return clientRepository.save(client);
    }

    // Find Client by ID method:
    public Optional<Client> findClientById(Long id) {
        return clientRepository.findById(id);
    }

    // Method to find Client by Dni
    public Optional<Client> findClientByDni(String dni) {
        return clientRepository.findClientByDni(dni);
    }

    // List all clients:
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    //Delete Client by ID
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }




}
