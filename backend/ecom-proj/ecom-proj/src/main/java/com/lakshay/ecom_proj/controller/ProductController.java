package com.lakshay.ecom_proj.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


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

    //Requestbody accepts the whole object
    //Request part accept the part of it
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){

                                        try{
                                            Product product1 = service.addProduct(product , imageFile);
                                            return new ResponseEntity<>(product1 , HttpStatus.CREATED);
                                        }    
                                        catch(Exception e){
                                            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                                        }

    }

    //method to send image
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductID(@PathVariable int productId){
        Product product = service.getProductByID(productId);
        byte[] imageFile = product.getImageData();

        //sending image back
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType()))
        .body(imageFile);
    }

    //updating product method
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id , @RequestPart Product product,
                                                @RequestPart MultipartFile imageFile){
        Product product1;
        try {
            product1 = service.updateProduct(id , product , imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("failed to update" , HttpStatus.BAD_REQUEST);
        }
        if(product1 != null){
            return new ResponseEntity<>("updated" , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("failed to update" , HttpStatus.BAD_REQUEST);
        }                                                  
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = service.getProductByID(id);
        if(product != null){
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted" , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Product not found " , HttpStatus.NOT_FOUND);
        }
    }

}
