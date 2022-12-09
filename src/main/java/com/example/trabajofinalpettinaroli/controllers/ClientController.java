package com.example.trabajofinalpettinaroli.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @GetMapping(value = "hola/mundo")
    public ResponseEntity<?> holaMundo() {
        return ResponseEntity.ok("Hola Mundo SOQUETES!!!!");
    }
}
