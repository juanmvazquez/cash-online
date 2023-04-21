package com.challengecashonline.cashonline.controller;

import com.challengecashonline.cashonline.model.dto.RequestUserDto;
import com.challengecashonline.cashonline.model.dto.ResponseUserDto;
import com.challengecashonline.cashonline.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@ValidateOnExecution
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<ResponseUserDto> createUser(@Valid @RequestBody RequestUserDto user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseUserDto>> getAllUser(){
        return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUserDto> updateUser(@PathVariable Long id, @RequestBody RequestUserDto user){
        return new ResponseEntity<>(userService.update(id, user), HttpStatus.OK);
    }

    @GetMapping("/get-user-by-name")
    public ResponseEntity<Page<ResponseUserDto>> getUserByFirstName(@RequestParam("firstName") String firstName, Pageable pageable){
        return new ResponseEntity<>(userService.findByFirstName(pageable, firstName), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }
}
