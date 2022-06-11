package com.example.librarymanagementsoftware.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(unique = true)
    @Email(message = "Your email must be a real email")
    @NotEmpty(message = "Your email must be a not empty")
    private String email;
    @Column(unique = true)
    @NotEmpty(message = "Username is required")
    @Length(min = 6,max = 20, message = "Username must be between 6 and 20 length range")
    private String username;
    @NotEmpty(message = "Your password must be a not Empty")
    @Size(min = 8,message = "your password must be a more 8 char")
    private String password;
    @Column(unique = true)
    private String phoneNumber;
    @Pattern(regexp = "(Admin|User)",message = "role can either be admin or user")
    @NotEmpty(message = "Role is required")
    private String role;
    @Positive(message = "It should be a number")
    private Double balance;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Books> booksSet;

    @OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Cart cart;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}