package com.challengecashonline.cashonline.service.impl;

import com.challengecashonline.cashonline.model.dto.RequestLoanDto;
import com.challengecashonline.cashonline.model.dto.ResponseLoanDto;
import com.challengecashonline.cashonline.model.entity.Loan;
import com.challengecashonline.cashonline.model.exception.LoanNotFoundException;
import com.challengecashonline.cashonline.model.mapper.LoanMapper;
import com.challengecashonline.cashonline.repository.LoanRepository;
import com.challengecashonline.cashonline.repository.UserRepository;
import com.challengecashonline.cashonline.service.LoanService;
import com.challengecashonline.cashonline.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LoanMapper loanMapper;

    @Override
    @Transactional
    public ResponseLoanDto saveLoan(RequestLoanDto loan) {
        Loan newLoan = Loan.builder()
                .total(loan.getTotal())
                .user(userRepository.findById(loan.getUser()).orElseThrow())
                .build();

        return loanMapper.mapToLoanDto(loanRepository.save(newLoan));
    }

    @Override
    public ResponseLoanDto findLoanById(Long id) {
        Optional<Loan> loanOptional = loanRepository.findById(id);
        Loan user = loanOptional.orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));
        return loanMapper.mapToLoanDto(user);
    }

    @Override
    @Transactional
    public ResponseLoanDto update(Long id, RequestLoanDto loan) {
        Optional<Loan> loanOptional = loanRepository.findById(id);
        if (loanOptional.isPresent()){
            Loan newLoan = loanOptional.get();

            newLoan.setTotal(loan.getTotal());

            Loan saveLoan = loanRepository.save(newLoan);

            return loanMapper.mapToLoanDto(saveLoan);
        }
        throw new EntityNotFoundException("Loan with id " + id + " not found.");
    }

    @Override
    public Page<ResponseLoanDto> findAll(Pageable pageable){

        Pageable newPageable = PageRequest.of(
                pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                pageable.getSort());

        Page<Loan> loans = loanRepository.findAll(newPageable);

        return loanMapper.toPageDto(loans);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try{
            if(loanRepository.existsById(id)){
                loanRepository.deleteById(id);
            }
        } catch (InvalidDataAccessApiUsageException e){
            throw new LoanNotFoundException(String.format("Loan not found with id: %s", id));
        }
    }
}
