package com.lakshay.ecom_proj.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Date release;
    private int quantity;
    private boolean availability;

}
