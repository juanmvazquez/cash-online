package com.challengecashonline.cashonline.service;

import com.challengecashonline.cashonline.model.dto.RequestLoanDto;
import com.challengecashonline.cashonline.model.dto.ResponseLoanDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanService {

    ResponseLoanDto saveLoan(RequestLoanDto loan);

    ResponseLoanDto findLoanById(Long id);

    ResponseLoanDto update(Long id, RequestLoanDto loan);

    Page<ResponseLoanDto> findAll(Pageable pageable);

    void delete (Long id);
}