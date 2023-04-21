package com.challengecashonline.cashonline.service;

import com.challengecashonline.cashonline.model.dto.RequestUserDto;
import com.challengecashonline.cashonline.model.dto.ResponseUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    ResponseUserDto saveUser (RequestUserDto user);

    ResponseUserDto findUserById (Long id);

    List<ResponseUserDto> findAllUser ();

    ResponseUserDto update(Long id, RequestUserDto user);

    Page<ResponseUserDto> findByFirstName(Pageable pageable, String firstName);

    void delete (Long id);
}
