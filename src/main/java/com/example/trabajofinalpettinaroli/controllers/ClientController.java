package com.example.trabajofinalpettinaroli.controllers;

import com.example.trabajofinalpettinaroli.entities.Client;
import com.example.trabajofinalpettinaroli.services.ClientService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(value = "/listar")
    public ResponseEntity<?> getAllClients() {
        List<Client> clients = clientService.findAllClients();
        try {
            if(!clients.isEmpty()) {
                return ResponseEntity.ok(clients);
            } else {
                return new ResponseEntity<>("No se encontraron clientes en la lista", HttpStatus.OK);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Ocurrió un error");
        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable (name = "id") Long id) {
        try {
            Optional<Client> client = clientService.findClientById(id);

            if(client.isPresent()) {
                return ResponseEntity.ok(client);
            } else {
                return new ResponseEntity<>("No se encontró ningún cliente con id " + id, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Ocurrió un error");
        }

    }

    @PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addClient(@RequestBody Client client) {
        try {
            Optional<Client> checkClient = clientService.findClientByDni(client.getDni());
            if(checkClient.isPresent()) {
                return new ResponseEntity<>("El cliente ya se encuentra registrado", HttpStatus.CONFLICT);
            } else {
                Client savedClient = clientService.createClient(client);
                return ResponseEntity.ok(savedClient);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Ocurrió un error");
        }
    }

    @PutMapping(value = "/modificar/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") Long id, @RequestBody Client client) {
        Optional<Client> clientData = clientService.findClientById(id);
        try {
            if(clientData.isPresent()) {
                // Setteo los valores previos con los nuevos.
                Client updateClient = clientData.get();
                updateClient.setClientName(client.getClientName());
                updateClient.setClientSurname(client.getClientSurname());
                updateClient.setDni(client.getDni());
                // Le asigno el nuevo cliente al viejo y lo guardo.
                return new ResponseEntity<>(clientService.createClient(updateClient), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("El cliente no existe", HttpStatus.NOT_FOUND);
            }
        }
        catch ( Exception e ) {
            return ResponseEntity.internalServerError().body("Ocurrió un error");
        }
    }

    @DeleteMapping(value = "/borrar/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            Optional<Client> client = clientService.findClientById(id);
            if(client.isPresent()) {
                clientService.deleteClient(id);
                return ResponseEntity.ok("Se ha borrado el cliente de Id " + id);
            } else {
                return new ResponseEntity<>("El cliente no existe", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Hubo un error");
        }
    }




}
