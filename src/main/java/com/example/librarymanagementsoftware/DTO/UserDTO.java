package com.example.librarymanagementsoftware.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class UserDTO {
    private Integer cartId;
    private Integer userId;
    private Double balance;
}
