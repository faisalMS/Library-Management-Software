package com.example.librarymanagementsoftware.Service;

import com.example.librarymanagementsoftware.Exception.UsersNotFoundException;
import com.example.librarymanagementsoftware.Model.Users;
import com.example.librarymanagementsoftware.Repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{
    private final UsersRepository usersRepository;

    public MyUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsersNotFoundException {
        Users users = usersRepository.findUsersByUsername(username);

        if(users == null){
            throw new UsernameNotFoundException("Username not found");
        }

        return users;
    }
}
