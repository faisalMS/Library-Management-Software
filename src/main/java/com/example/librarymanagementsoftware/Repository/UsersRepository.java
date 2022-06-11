package com.example.librarymanagementsoftware.Repository;

import com.example.librarymanagementsoftware.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findUsersByUsername(String username);
}