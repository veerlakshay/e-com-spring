package com.lakshay.ecom_proj.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//creating class as entity
@Entity
//using lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    //using id as primary key
    @Id
    //generating primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    //product variables
    private String name;
    private String description;
    private String brand;
    private double price;
    private String category;
    private Date releaseDate;
    private int stockQuantity;
    private boolean productAvailable;

    //We should have done it by storing image in cloud and pass the url
    //It is a good way, but just to keep it simple we are storing image in database
    //It is not a good way
    private String imageName;
    private String imageType;

    //We are using byte array because we are going to stor it in byte format

    //large object
    @Lob
    private byte[] imageData;

}
