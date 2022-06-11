package com.example.librarymanagementsoftware.Controller;

import com.example.librarymanagementsoftware.DTO.Api;
import com.example.librarymanagementsoftware.Model.Loan;
import com.example.librarymanagementsoftware.Service.LoanService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/Loan")
@RequiredArgsConstructor
public class LoanController {
    Logger logger = LoggerFactory.getLogger(LoanController.class);
    private final LoanService loanService;
    // Get all loan
    @GetMapping
    public ResponseEntity<List<Loan>> getLoan(){
        logger.info("getLoan");
        return ResponseEntity.status(HttpStatus.OK).body(loanService.getLoan());
    }
    // Add loan
    @PostMapping
    public ResponseEntity<Api> addLoan(@RequestBody @Valid Loan loan){
        logger.info("addLoan");
        loanService.addLoan(loan);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Loan added!", 200));
    }
    // Delete loan
    @DeleteMapping("/{loanId}")
    public ResponseEntity<Api> deleteLoan(@PathVariable Integer loanId){
        loanService.deleteLoan(loanId);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("loan deleted !", 200));
    }
}