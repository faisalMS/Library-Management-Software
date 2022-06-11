package com.example.librarymanagementsoftware.DTO;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data
public class BooksDTO {
    private Integer userId;
    private Integer booksId;
    private Integer cartId;
}