package com.example.librarymanagementsoftware.Controller;

import com.example.librarymanagementsoftware.DTO.Api;
import com.example.librarymanagementsoftware.DTO.BooksDTO;
import com.example.librarymanagementsoftware.DTO.CartDTO;
import com.example.librarymanagementsoftware.Model.Users;
import com.example.librarymanagementsoftware.Service.UsersService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UsersController {
    Logger logger = LoggerFactory.getLogger(UsersController.class);
    private final UsersService usersService;
    // Get all users
    @GetMapping
    public ResponseEntity<List<Users>> getUsers(){
        logger.info("getUsers");
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getUsers());
    }
    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody @Valid Users user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));
        }
        usersService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("New User added",200));
    }
    @GetMapping ("/logged")
    public ResponseEntity<String> logged(){
        return ResponseEntity.status(HttpStatus.OK).body("logged");
    }
    @GetMapping("/user")
    public ResponseEntity<?> user(){
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Hello User",200));
    }
    @GetMapping("/admin")
    public ResponseEntity<?> admin(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello Admin");
    }

    // Update users
    @PutMapping("/{usersId}")
    public ResponseEntity<Api> updateUsers(@RequestBody @Valid Users users, @PathVariable Integer usersId){
        usersService.updateUsers(users,usersId);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("User update !", 200));
    }
    // Delete users
    @DeleteMapping("/{usersId}")
    public ResponseEntity<Api> deleteUsers(@PathVariable Integer usersId){
        usersService.deleteUsers(usersId);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Users deleted !", 200));
    }
    // Add book to user
    @PostMapping("/add/addUser")
    public ResponseEntity addUserToBook(@RequestBody BooksDTO booksDTO){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.addUserToBook(booksDTO));
    }

    // create cart for user
    @PostMapping("/addCartToUser")
    public ResponseEntity addCartToUser(@RequestBody CartDTO cartDTO) {
        logger.info("Cart has been Added!");
        usersService.addCartToUser(cartDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Cart ID: ("+cartDTO.getCartID()+"), Payment Method: ("+cartDTO.getPaymentMethod()+") has been added to User!");

    }
}