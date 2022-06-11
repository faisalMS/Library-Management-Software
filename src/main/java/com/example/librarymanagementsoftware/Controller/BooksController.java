package com.example.librarymanagementsoftware.Controller;

import com.example.librarymanagementsoftware.DTO.Api;
import com.example.librarymanagementsoftware.DTO.BooksDTO;
import com.example.librarymanagementsoftware.DTO.CartDTO;
import com.example.librarymanagementsoftware.Model.Books;
import com.example.librarymanagementsoftware.Service.BooksService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BooksController {
    Logger logger = LoggerFactory.getLogger(BooksController.class);
    private final BooksService booksService;

    //Get all Books
    @GetMapping
    public ResponseEntity<List<Books>> getBooks(){
        logger.info("getBooks");
        return ResponseEntity.status(HttpStatus.OK).body(booksService.getBooks());
    }
    // Add Books
    @PostMapping
    public ResponseEntity<Api> addBooks(@RequestBody @Valid Books books){
        logger.info("addBooks");
        booksService.addBooks(books);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Book added !", 200));
    }
    // Update books
    @PutMapping("/{bookId}")
    public ResponseEntity<Api> updateBooks(@RequestBody @Valid Books books,@PathVariable Integer bookId){
        booksService.updateBooks(books,bookId);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Book update !", 200));
    }
    // Delete books
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Api> deleteBooks(@PathVariable Integer bookId){
        booksService.deleteBooks(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Book deleted !", 200));
    }
    @GetMapping("/{genre}")
    public ResponseEntity<Api> genreBooks(@PathVariable String genre){
        booksService.genreBooks(genre);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Book type selected", 200));
    }
    //Lend books
    @PostMapping("/lend/{bookId}/{userId}")
    public ResponseEntity lendBookToUser(@PathVariable Integer bookId,@PathVariable Integer userId){
        logger.info("book : " + bookId + "is being lent to user : " + userId + " through lendBookToUser");
        booksService.lendBookToUser(bookId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Loan successfully done",200));
    }
    @PostMapping("/return/{bookId}")
    public ResponseEntity returnBook(@PathVariable Integer bookId){
        logger.info("book" + bookId + "is being returnBook");
        return ResponseEntity.status(HttpStatus.OK).body(booksService.returnBook(bookId));
    }

    @PostMapping("/addCartToBook")
    public ResponseEntity addCartToBook(@RequestBody CartDTO cartDTO){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.addCartToBook(cartDTO));
    }
}