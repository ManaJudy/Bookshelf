package com.mana.bookshelf.controller;

import com.mana.bookshelf.dto.LoanTypeDTO;
import com.mana.bookshelf.service.LoanTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/loan-types")
public class LoanTypeController {
    private final LoanTypeService loanTypeService;

    public LoanTypeController(LoanTypeService loanTypeService) {
        this.loanTypeService = loanTypeService;
    }

    @GetMapping
    public ResponseEntity<List<LoanTypeDTO>> getLoanTypes() {
        List<LoanTypeDTO> loanTypes = loanTypeService.getLoanTypes();
        return new ResponseEntity<>(loanTypes, HttpStatus.OK);
    }
}
