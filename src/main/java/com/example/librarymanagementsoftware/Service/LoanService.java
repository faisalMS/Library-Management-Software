package com.example.librarymanagementsoftware.Service;

import com.example.librarymanagementsoftware.Model.Loan;
import com.example.librarymanagementsoftware.Repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    public List<Loan> getLoan(){
        return loanRepository.findAll();
    }
    public void addLoan(Loan loan){
        loanRepository.save(loan);
    }
    public void deleteLoan(Integer loanId) {
        boolean exists = loanRepository.existsById(loanId);
        if(!exists){
            throw new IllegalStateException("loan with id" + loanId + "dose not exists");
        }
        loanRepository.deleteById(loanId);
    }
}