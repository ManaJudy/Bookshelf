package com.mana.bookshelf.converter.entitytodto;

import com.mana.bookshelf.dto.LoanTypeDTO;
import com.mana.bookshelf.entity.LoanType;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LoanTypeToLoanTypeDTO implements Function<LoanType, LoanTypeDTO> {

    @Override
    public LoanTypeDTO apply(LoanType loanType) {
        if (loanType == null) return null;
        LoanTypeDTO loanTypeDTO = new LoanTypeDTO();
        loanTypeDTO.setId(loanType.getId());
        loanTypeDTO.setName(loanType.getName());
        loanTypeDTO.setMaxLoanDays(loanType.getMaxLoanDays());
        return loanTypeDTO;
    }
}
