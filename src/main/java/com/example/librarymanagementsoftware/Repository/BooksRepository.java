package com.example.librarymanagementsoftware.Repository;

import com.example.librarymanagementsoftware.Model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
    List<Books> findByGenre(String genre);
}