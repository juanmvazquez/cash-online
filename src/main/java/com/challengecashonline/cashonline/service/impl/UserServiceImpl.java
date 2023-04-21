package com.challengecashonline.cashonline.service.impl;

import com.challengecashonline.cashonline.model.dto.RequestUserDto;
import com.challengecashonline.cashonline.model.dto.ResponseUserDto;
import com.challengecashonline.cashonline.model.entity.User;
import com.challengecashonline.cashonline.model.exception.DuplicatedUserException;
import com.challengecashonline.cashonline.model.exception.UserNotFoundException;
import com.challengecashonline.cashonline.model.mapper.UserMapper;
import com.challengecashonline.cashonline.repository.UserRepository;
import com.challengecashonline.cashonline.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public ResponseUserDto saveUser(RequestUserDto user) {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        try{
            return userMapper.mapUserToDto(userRepository.save(newUser));
        } catch (DataIntegrityViolationException e){
            throw new DuplicatedUserException(
                    String.format("There is already a user whit email: %s", user.getEmail())
            );
        }
    }

    @Override
    public ResponseUserDto findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return userMapper.mapUserToDto(user);
    }

    @Override
    public List<ResponseUserDto> findAllUser() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::mapUserToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseUserDto update(Long id, RequestUserDto user){
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()){
            User newUser = userOptional.get();

            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());

            User saveUser = userRepository.save(newUser);

            return userMapper.mapUserToDto(saveUser);
        }
        throw new UserNotFoundException("User not found with id: " + id);
    }

    @Override
    public Page<ResponseUserDto> findByFirstName(Pageable pageable, String firstName){

        Pageable newPageable = PageRequest.of(
                pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                pageable.getSort());

        Page<User> users = userRepository.findByFirstNameIgnoreCase(newPageable,firstName);
        return userMapper.toPageDto(users);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try{
            userRepository.findById(id).ifPresentOrElse(
                    user -> userRepository.deleteById(id),
                    () -> {
                        throw new UserNotFoundException(String.format("User with id: %d not found", id));
                    });
            userRepository.deleteById(id);
        } catch (DataAccessException e){
            throw new UserNotFoundException(String.format("You are using an invalid user id: %d", id));
        }

    }
}
