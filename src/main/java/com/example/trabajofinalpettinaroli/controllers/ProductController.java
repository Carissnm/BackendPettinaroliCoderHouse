package com.example.trabajofinalpettinaroli.controllers;


import com.example.trabajofinalpettinaroli.entities.Product;
import com.example.trabajofinalpettinaroli.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/listar")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = productService.findAllProducts();
            if(!products.isEmpty()) {
                return ResponseEntity.ok(products);
            } else {
                return new ResponseEntity<>("No se encontraron productos en la lista", HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ocurrió un error");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(name="id") Long id) {
        try {
            Optional<Product> product = productService.findProductById(id);

            if(product.isPresent()) {
                return ResponseEntity.ok(product);
            } else {
                return new ResponseEntity<>("No se encontró ningún producto con id " + id, HttpStatus.NOT_FOUND);
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Ocurrió un error");
        }

    }

    @PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            Optional<Product> checkProduct = productService.findProductByPrdCode(product.getProductCode());
            if(checkProduct.isPresent()) {
                return new ResponseEntity<>("El producto ya se encuentra registrado", HttpStatus.CONFLICT);
            } else {
                Product savedProduct = productService.createProduct(product);
                return ResponseEntity.ok(savedProduct);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Ocurrió un error");
        }
    }

    @PutMapping(value = "/modificar/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Optional<Product> productData = productService.findProductById(id);
        try {
            if(productData.isPresent()) {
                //Set de los valores nuevos de los atributos de Producto
                Product updateProduct = productData.get();
                updateProduct.setProductCode(product.getProductCode());
                updateProduct.setDescription(product.getDescription());
                updateProduct.setPrice(product.getPrice());
                updateProduct.setQuantity(product.getQuantity());
                // Guardo en la tabla el cambio
                return new ResponseEntity<>(productService.createProduct(updateProduct), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("El producto no existe", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Ocurrió un error");
        }
    }

    @DeleteMapping(value = "/borrar/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable (name = "id") Long id) {
        try {
            Optional<Product> product = productService.findProductById(id);
            if(product.isPresent()) {
                productService.deleteProduct(id);
                return ResponseEntity.ok("Producto de id " + id + " eliminado satisfactoriamente");
            } else {
                return new ResponseEntity<>("El producto no existe", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Ocurrió un error");
        }
    }
}
