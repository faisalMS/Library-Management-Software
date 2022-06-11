package com.example.librarymanagementsoftware.Service;

import com.example.librarymanagementsoftware.DTO.CartDTO;
import com.example.librarymanagementsoftware.Exception.InvalidIDException;
import com.example.librarymanagementsoftware.Model.Books;
import com.example.librarymanagementsoftware.Model.Cart;
import com.example.librarymanagementsoftware.Model.Loan;
import com.example.librarymanagementsoftware.Model.Users;
import com.example.librarymanagementsoftware.Repository.BooksRepository;
import com.example.librarymanagementsoftware.Repository.CartRepository;
import com.example.librarymanagementsoftware.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BooksService {
    private final BooksRepository booksRepository;
    private final UsersRepository usersRepository;

    private final CartRepository cartRepository;
    public List<Books> getBooks() {
        return booksRepository.findAll();
    }
    public void addBooks(Books books) {
        booksRepository.save(books);
    }
    public void updateBooks(Books newBooks, Integer bookId) {
        Books oldBooks = booksRepository.findById(bookId).get();
        oldBooks.setBookId(newBooks.getBookId());
        oldBooks.setBookName(newBooks.getBookName());
        oldBooks.setGenre(newBooks.getGenre());
        booksRepository.save(oldBooks);
    }
    public void deleteBooks(Integer bookId) {
        boolean exists = booksRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException("book with id" + bookId + "dose not exists");
        }
        booksRepository.deleteById(bookId);
    }
    public void genreBooks(String genre) {
        booksRepository.findByGenre(genre);
    }
    public void lendBookToUser(Integer bookId, Integer userId) {
        Optional<Books> book = booksRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new InvalidIDException("Book not found");
        }
        Optional<Users> user = usersRepository.findById(userId);
        if (user.isEmpty()) {
            throw new InvalidIDException("User not found");
        }
        Loan loan = new Loan();
        loan.setBookId(bookId);
        loan.setUserId(userId);
        loan.setBooksSet(new HashSet<>());
        book.get().getLoanSet().add(loan);
        book.get().setUsers(user.get());
        booksRepository.save(book.get());
    }
    public Set<Loan> returnBook(Integer bookId) {
        Optional<Books> book = booksRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new InvalidIDException("Book not found");
        }
        return book.get().getLoanSet();
    }

    public Object addCartToBook(CartDTO cartDTO) {
        Cart cart = cartRepository.findById(cartDTO.getBookId()).
                orElseThrow(()-> new InvalidIDException("Cart invalid"));
        Books books = booksRepository.findById(cartDTO.getBookId())
                .orElseThrow(()-> new InvalidIDException("Books invalid"));
        cart.getBooksSet().add(books);
        cartRepository.save(cart);
        books.setCarts(cart);
        booksRepository.save(books);
        return cartRepository.findAll();
    }
}