package com.challengecashonline.cashonline.testutils;

import com.challengecashonline.cashonline.model.dto.RequestLoanDto;
import com.challengecashonline.cashonline.model.dto.RequestUserDto;
import com.challengecashonline.cashonline.model.dto.ResponseLoanDto;
import com.challengecashonline.cashonline.model.dto.ResponseUserDto;
import com.challengecashonline.cashonline.model.entity.Loan;
import com.challengecashonline.cashonline.model.entity.User;


import java.util.ArrayList;
import java.util.List;

public class TestEntityFactory {
    public static User getUser(){
        return User.builder()
                .firstName("juan")
                .lastName("vazquez")
                .email("juan@gmail.com")
                .build();
    }

    public static Loan getLoan(){
        return Loan.builder()
                .total(20.00)
                .user(getUser()).build();
    }

    public static List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        userList.add(getUser());
        return userList;
    }

    public static RequestUserDto getRequestUserDto(){
        return RequestUserDto.builder()
                .firstName("juan")
                .lastName("vazquez")
                .email("juan@gmail.com")
                .build();
    }

    public static RequestLoanDto getRequestLoanDto(){
        return RequestLoanDto.builder().total(20.00).user(1L)
                .build();
    }

    public static User getNewUser(){
        return User.builder()
                .firstName("juan M")
                .lastName("vazquez")
                .email("juan@gmail.com")
                .build();
    }

    public static Loan getNewLoan(){
        return Loan.builder()
                .total(22.00)
                .user(getUser())
                .build();

    }

    public static ResponseUserDto getResponseUserDto(){
        return ResponseUserDto.builder()
                .firstName("juan")
                .lastName("vazquez")
                .email("juan@gmail.com")
                .build();
    }

    public static ResponseLoanDto getResponseLoanDto(){
        return ResponseLoanDto.builder().total(20.00).user(1L)
                .build();
    }



    public static ResponseUserDto getResponseNewUserDto(){
        return ResponseUserDto.builder()
                .firstName("juan M")
                .lastName("vazquez")
                .email("juan@gmail.com")
                .build();
    }

    public static ResponseLoanDto getResponseNewLoanDto(){
        return ResponseLoanDto.builder()
                .total(22.00)
                .user(getUser().getId())
                .build();
    }

    public static RequestUserDto getRequestNewUserDto(){
        return RequestUserDto.builder()
                .firstName("juan M")
                .lastName("vazquez")
                .email("juan@gmail.com")
                .build();
    }

    public static RequestLoanDto getRequestNewLoanDto(){
        return RequestLoanDto.builder()
                .total(22.00)
                .user(getUser().getId())
                .build();
    }

    public static User getUserById(){
        return User.builder()
                .id(1L)
                .firstName("juan")
                .lastName("vazquez")
                .email("juan@gmail.com")
                .build();
    }

    public static Loan getLoanById(){
        return Loan.builder()
                .id(1L)
                .total(20.00)
                .user(getUser()).build();
    }

    public static List<ResponseUserDto> getListResponseUserDto(){
        List<ResponseUserDto> responseUserDtoList = new ArrayList<>();
        responseUserDtoList.add(getResponseUserDto());
        return responseUserDtoList;
    }
}
