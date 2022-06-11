package com.example.librarymanagementsoftware.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanId;
    @NotNull(message = "User id is required")
    private Integer userId;
    @NotNull(message = "Book id is required")
    private Integer bookId;


    @ManyToMany(mappedBy = "loanSet", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Books> booksSet;
}