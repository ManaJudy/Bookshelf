package com.mana.bookshelf.service;

import com.mana.bookshelf.converter.entitytodto.LoanTypeToLoanTypeDTO;
import com.mana.bookshelf.dto.LoanTypeDTO;
import com.mana.bookshelf.repository.LoanTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;
    private final LoanTypeToLoanTypeDTO loanTypeToLoanTypeDTO;

    public LoanTypeService(LoanTypeRepository loanTypeRepository, LoanTypeToLoanTypeDTO loanTypeToLoanTypeDTO) {
        this.loanTypeRepository = loanTypeRepository;
        this.loanTypeToLoanTypeDTO = loanTypeToLoanTypeDTO;
    }

    public List<LoanTypeDTO> getLoanTypes() {
        return loanTypeRepository.findAll().stream()
                .map(loanTypeToLoanTypeDTO)
                .toList();
    }
}
