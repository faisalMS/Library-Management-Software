package com.example.librarymanagementsoftware.Service;

import com.example.librarymanagementsoftware.Model.Cart;
import com.example.librarymanagementsoftware.Repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    public List<Cart> getCart(){
        return cartRepository.findAll();
    }
    public void addCart(Cart cart){
        cartRepository.save(cart);
    }
    public void deleteCart(Integer cartId) {
        boolean exists = cartRepository.existsById(cartId);
        if(!exists){
            throw new IllegalStateException("Cart with id" + cartId + "dose not exists");
        }
        cartRepository.deleteById(cartId);
    }
}