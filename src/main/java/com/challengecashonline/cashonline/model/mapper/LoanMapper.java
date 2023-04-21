package com.challengecashonline.cashonline.model.mapper;

import com.challengecashonline.cashonline.model.dto.ResponseLoanDto;
import com.challengecashonline.cashonline.model.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanMapper {

    public ResponseLoanDto mapToLoanDto(Loan loan){
        ResponseLoanDto responseLoanDto = new ResponseLoanDto();
        responseLoanDto.setTotal(loan.getTotal());
        responseLoanDto.setUser(loan.getUser().getId());
        return responseLoanDto;
    }

    public Page<ResponseLoanDto> toPageDto (Page<Loan> loans){
        List<ResponseLoanDto> responseLoanDtoList = loans.getContent().stream()
                .map(this::toDTo)
                .collect(Collectors.toList());
        return new PageImpl<>(responseLoanDtoList, loans.getPageable(), loans.getTotalElements());
    }

    public ResponseLoanDto toDTo(Loan loan){
        return new ResponseLoanDto(loan.getTotal(), loan.getUser().getId());
    }
}
