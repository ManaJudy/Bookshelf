package com.mana.bookshelf.controller;

import com.mana.bookshelf.dto.LoanDTO;
import com.mana.bookshelf.dto.ReturnLoanRequestDTO;
import com.mana.bookshelf.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanDTO loanDTO) {
        LoanDTO createdLoan = loanService.createLoan(loanDTO);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<LoanDTO> returnLoan(@PathVariable Long id, @RequestBody ReturnLoanRequestDTO returnLoanRequest) {
        LoanDTO returnedLoan = loanService.returnLoan(id, returnLoanRequest.getReturnDate());
        return new ResponseEntity<>(returnedLoan, HttpStatus.OK);
    }

    @PostMapping("/{id}/extend")
    public ResponseEntity<LoanDTO> extendLoan(@PathVariable Long id) {
        LoanDTO extendedLoan = loanService.extendLoan(id);
        return new ResponseEntity<>(extendedLoan, HttpStatus.OK);
    }
}
