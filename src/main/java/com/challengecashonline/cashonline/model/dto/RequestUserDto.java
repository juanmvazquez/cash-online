package com.challengecashonline.cashonline.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDto {

    @NotEmpty(message = "The name must not be empty.")
    private String firstName;

    @NotEmpty(message = "The last name must not be empty.")
    private String lastName;

    @NotEmpty(message = "The email must not be empty.")
    @Email(message = "It is an invalid email format.")
    private String email;
}
