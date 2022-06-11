package com.example.librarymanagementsoftware.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class CartDTO {
    private Integer cartID;
    private String PaymentMethod;
    private Integer userID;
    private Integer bookID;
}
