package com.challengecashonline.cashonline.model.mapper;

import com.challengecashonline.cashonline.model.dto.ResponseLoanDto;
import com.challengecashonline.cashonline.model.dto.ResponseUserDto;
import com.challengecashonline.cashonline.model.entity.Loan;
import com.challengecashonline.cashonline.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    LoanMapper loanMapper;

        public ResponseUserDto mapUserToDto(User user) {
            ResponseUserDto responseUserDto = new ResponseUserDto();
            responseUserDto.setFirstName(user.getFirstName());
            responseUserDto.setLastName(user.getLastName());
            responseUserDto.setEmail(user.getEmail());

            if(!Objects.isNull(user.getLoans())){
                responseUserDto.setLoans(user.getLoans().stream().map(this::mapToLoanDto).collect(Collectors.toList()));
            }

            return responseUserDto;
        }

        private ResponseLoanDto mapToLoanDto(Loan loan) {
            ResponseLoanDto responseLoanDto = new ResponseLoanDto();
            responseLoanDto.setTotal(loan.getTotal());
            responseLoanDto.setUser(loan.getUser().getId());
            return responseLoanDto;
        }

    public Page<ResponseUserDto> toPageDto (Page<User> users){
        List<ResponseUserDto> responseUserDtoList = users.getContent().stream()
                .map(this::toDTo)
                .collect(Collectors.toList());
        return new PageImpl<>(responseUserDtoList, users.getPageable(), users.getTotalElements());
    }

    public ResponseUserDto toDTo(User user){
        List<ResponseLoanDto> loans = user.getLoans().stream()
                .map(loan -> loanMapper.toDTo(loan))
                .collect(Collectors.toList());
        return new ResponseUserDto(user.getFirstName(), user.getLastName(), user.getEmail(), loans);
    }
}
