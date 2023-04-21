package com.challengecashonline.cashonline.controller;

import com.challengecashonline.cashonline.model.dto.RequestLoanDto;
import com.challengecashonline.cashonline.model.dto.ResponseLoanDto;
import com.challengecashonline.cashonline.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/save")
    public ResponseEntity<ResponseLoanDto> createLoan(@RequestBody RequestLoanDto loan){
        return new ResponseEntity<>(loanService.saveLoan(loan), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseLoanDto> getLoanById(@PathVariable Long id){
        return new ResponseEntity<>(loanService.findLoanById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseLoanDto> updateLoan(@PathVariable Long id, @RequestBody RequestLoanDto loan){
        return new ResponseEntity<>(loanService.update(id, loan), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Page<ResponseLoanDto>> getAllLoans(Pageable pageable){

        return new ResponseEntity<>(loanService.findAll(pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id){
        loanService.delete(id);
    }
}
