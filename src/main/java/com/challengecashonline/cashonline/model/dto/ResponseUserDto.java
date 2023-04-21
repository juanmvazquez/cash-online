package com.challengecashonline.cashonline.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUserDto {


    private String firstName;
    private String lastName;
    private String email;
    private List<ResponseLoanDto> loans = new ArrayList<>();
}
