package com.example.librarymanagementsoftware.Service;

import com.example.librarymanagementsoftware.DTO.BooksDTO;
import com.example.librarymanagementsoftware.DTO.CartDTO;
import com.example.librarymanagementsoftware.Exception.InvalidIDException;
import com.example.librarymanagementsoftware.Model.Books;
import com.example.librarymanagementsoftware.Model.Cart;
import com.example.librarymanagementsoftware.Model.Users;
import com.example.librarymanagementsoftware.Repository.BooksRepository;
import com.example.librarymanagementsoftware.Repository.CartRepository;
import com.example.librarymanagementsoftware.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final BooksRepository booksRepository;
    private final CartRepository cartRepository;

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }
    public void addUser(Users user) {
        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
        usersRepository.save(user);
    }
    public void updateUsers(Users newUsers, Integer usersId) {
        Users oldUsers = usersRepository.findById(usersId).get();
        oldUsers.setUserId(newUsers.getUserId());
        oldUsers.setBalance(newUsers.getBalance());
        oldUsers.setUsername(newUsers.getUsername());
        oldUsers.setPassword(newUsers.getPassword());
        oldUsers.setPhoneNumber(newUsers.getPhoneNumber());
        usersRepository.save(oldUsers);
    }
    public void deleteUsers(Integer usersId) {
        boolean exists = usersRepository.existsById(usersId);
        if(!exists){
            throw new IllegalStateException("Users with id" + usersId + "dose not exists");
        }
        usersRepository.deleteById(usersId);
    }
    public Object addUserToBook(BooksDTO booksDTO) {
        Users users = usersRepository.findById(booksDTO.getUserId()).
                orElseThrow(()-> new InvalidIDException("User invalid"));
        Books books = booksRepository.findById(booksDTO.getBooksId())
                .orElseThrow(()-> new InvalidIDException("book invalid"));
        users.getBooksSet().add(books);
        usersRepository.save(users);
        books.setUsers(users);
        booksRepository.save(books);
        return booksRepository.findAll();
    }

    public Object addCartToUser(CartDTO cartDTO) {
        Users user = usersRepository.findById(cartDTO.getCartID())
                .orElseThrow(() -> new InvalidIDException("user id is invalid"));
        Cart cart = new Cart(null, cartDTO.getPaymentMethod(), cartDTO.getUserID(),cartDTO.getBookID(), user);
        cartRepository.save(cart);
        usersRepository.save(user);
        return usersRepository.findAll();

    }
}