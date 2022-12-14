package com.example.trabajofinalpettinaroli.controllers;

import com.example.trabajofinalpettinaroli.dto.ReceiptDTO;
import com.example.trabajofinalpettinaroli.entities.Receipt;
import com.example.trabajofinalpettinaroli.services.ReceiptService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comprobante")
public class ReceiptController {

    @Autowired
    ReceiptService receiptService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getClientById(@PathVariable(name = "id") Long id) {
        try {
            Optional<Receipt> receipt = receiptService.getReceiptById(id);

            if(receipt.isPresent()) {
                return ResponseEntity.ok(receipt);
            } else {
                return new ResponseEntity<>("No se encontró ningún comprobante con id " + id, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }

    }

    @GetMapping(value = "/listar")
    public ResponseEntity<?> getAllReceipts() {
        try {
            List<Receipt> receipts = receiptService.getAllReceipts();
            if(!receipts.isEmpty()) {
                return ResponseEntity.ok(receipts);
            } else {
                return new ResponseEntity<>("No se encontraron comprobantes en la lista", HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }

    @DeleteMapping(value = "/borrar/{id}") // Deletes a receipt selected by Id.
    public ResponseEntity<?> deleteReceipt(@PathVariable(name = "id") Long id) {
        try {
            Optional<Receipt> receiptToDelete = receiptService.getReceiptById(id);
            if(receiptToDelete.isPresent()) {
                receiptService.deleteReceipt(id);
                return ResponseEntity.ok("Comprobante de id " + id + " eliminado correctamente");
            } else {
                return new ResponseEntity<>("El comprobante de id " + id + " no existe", HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }

    @PostMapping(value = "/agregar/comprobante")
    public ReceiptDTO createReceiptDTO(@RequestBody Receipt receipt) {
        return this.receiptService.saveReceipt(receipt);
    } // the receipt comes from the frontend with all the information regarding the client who's
    // making the purchase, all the items picked and their quantities as lines.
    // the backend receives it and saves everything on each db table and returns the "ticket" saved.


}
