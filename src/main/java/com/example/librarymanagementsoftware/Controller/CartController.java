package com.example.librarymanagementsoftware.Controller;

import com.example.librarymanagementsoftware.DTO.Api;
import com.example.librarymanagementsoftware.DTO.BooksDTO;
import com.example.librarymanagementsoftware.DTO.UserDTO;
import com.example.librarymanagementsoftware.Model.Cart;
import com.example.librarymanagementsoftware.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    Logger logger = LoggerFactory.getLogger(CartController.class);
    private final CartService cartService;
    @GetMapping
    public ResponseEntity<List<Cart>> getCart(){
        logger.info("getCart");
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCart());
    }
    @PostMapping
    public ResponseEntity<Api> addCart(@RequestBody @Valid Cart cart){
        logger.info("addCart");
        cartService.addCart(cart);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("cart added!", 200));
    }
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Api> deleteCart(@PathVariable Integer cartId){
        cartService.deleteCart(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("cart deleted !", 200));
    }
}