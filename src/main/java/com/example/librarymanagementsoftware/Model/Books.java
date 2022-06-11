package com.example.librarymanagementsoftware.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Set;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    @NotEmpty(message = "Your book name must be a not Empty")
    private String bookName;
    @Pattern(regexp = "(Adventure|Action|Science)", message ="Genre can either be Adventure, Action or Science")
    private String genre;
    @Positive(message = "It should be a number")
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    private Users users;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cart carts;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Loan> loanSet;
}