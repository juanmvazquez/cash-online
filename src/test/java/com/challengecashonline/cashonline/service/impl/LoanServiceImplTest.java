package com.challengecashonline.cashonline.service.impl;

import com.challengecashonline.cashonline.model.dto.RequestLoanDto;
import com.challengecashonline.cashonline.model.dto.ResponseLoanDto;
import com.challengecashonline.cashonline.model.entity.Loan;
import com.challengecashonline.cashonline.model.entity.User;
import com.challengecashonline.cashonline.model.mapper.LoanMapper;
import com.challengecashonline.cashonline.repository.LoanRepository;
import com.challengecashonline.cashonline.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getLoan;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getLoanById;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getNewLoan;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getRequestLoanDto;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getRequestNewLoanDto;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getResponseLoanDto;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getResponseNewLoanDto;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {

    @Mock
    private LoanMapper loanMapper;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    void saveLoan() {
        RequestLoanDto requestLoanDto = getRequestLoanDto();
        ResponseLoanDto expectedResponse = getResponseLoanDto();
        User user = getUser();

        when(userRepository.findById(requestLoanDto.getUser())).thenReturn(Optional.of(user));
        when(loanRepository.save(getLoan())).thenReturn(getLoan());
        when(loanMapper.mapToLoanDto(getLoan())).thenReturn(expectedResponse);

        ResponseLoanDto actualResponse = loanService.saveLoan(requestLoanDto);

        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    void findLoanById() {
        ResponseLoanDto expectedResponse = getResponseLoanDto();

        when(loanRepository.findById(1L)).thenReturn(Optional.of(getLoanById()));
        when(loanMapper.mapToLoanDto(getLoanById())).thenReturn(expectedResponse);

        ResponseLoanDto actualResponse = loanService.findLoanById(1L);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void update() {
        Loan updateLoan = getNewLoan();
        ResponseLoanDto expectedResponse = getResponseNewLoanDto();

        when(loanRepository.findById(1L)).thenReturn(Optional.of(getLoanById()));
        Loan savedLoan = loanRepository.save(updateLoan);
        when(loanMapper.mapToLoanDto(savedLoan)).thenReturn(expectedResponse);
        ResponseLoanDto actualResponse = loanService.update(1L, getRequestNewLoanDto());

        assertEquals(expectedResponse.getTotal(), actualResponse.getTotal());
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
        loanService.delete(1L);
        verify(loanRepository, times(1)).deleteById(1L);
    }
}