package com.lakshay.ecom_proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lakshay.ecom_proj.model.Product;
import com.lakshay.ecom_proj.service.ProductService;

//class as controller
@RestController
// cross origin to connect frontend and backend running on different ports
@CrossOrigin
// creating endpoint
@RequestMapping("/api")
public class ProductController {

    // getting service obj
    @Autowired
    private ProductService service;

    // creating endpoint
    // using responseentity to send status code with list
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        // calling methoud from service
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    // creating end point
    // reciving prodID from url
    // using responseentity to send status code with list
    @GetMapping("/product/{prodId}")
    public ResponseEntity<Product> getProductByID(@PathVariable int prodId) {
        // product validation
        Product product = service.getProductByID(prodId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // calling method from service

}
