package com.example.librarymanagementsoftware.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    @NotEmpty(message = "paymentMethod is required")
    @Pattern(regexp = "(Cash|Credit Card)", message = "Payment method most be Cash or Credit Card")
    private String paymentMethod;
    

    @OneToOne(cascade  = CascadeType.ALL)
    @JsonIgnore
    private Users users;

    @OneToMany(mappedBy = "carts", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Books> booksSet;


}
