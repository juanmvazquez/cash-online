package com.challengecashonline.cashonline.service.impl;

import com.challengecashonline.cashonline.model.dto.ResponseUserDto;
import com.challengecashonline.cashonline.model.entity.User;
import com.challengecashonline.cashonline.model.mapper.UserMapper;
import com.challengecashonline.cashonline.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getListResponseUserDto;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getNewUser;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getRequestNewUserDto;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getRequestUserDto;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getResponseNewUserDto;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getResponseUserDto;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getUser;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getUserById;
import static com.challengecashonline.cashonline.testutils.TestEntityFactory.getUserList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void saveUser() {
        ResponseUserDto expectedResonse = getResponseUserDto();

        when(userRepository.save(getUser())).thenReturn(getUser());
        when(userMapper.mapUserToDto(getUser())).thenReturn(expectedResonse);

        ResponseUserDto actualResponse = userService.saveUser(getRequestUserDto());
        assertEquals(expectedResonse, actualResponse);
    }

    @Test
    void findUserById() {

        ResponseUserDto expectedResponse = getResponseUserDto();

        when(userRepository.findById(1L)).thenReturn(Optional.of(getUserById()));
        when(userMapper.mapUserToDto(getUserById())).thenReturn(expectedResponse);

        ResponseUserDto actualResponse = userService.findUserById(1L);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void findAllUser() {

        List<User> userList = getUserList();
        when(userRepository.findAll()).thenReturn(userList);

        List<ResponseUserDto> expectedResponseList = getListResponseUserDto();
        List<ResponseUserDto> actualResponseList = userService.findAllUser();

        assertEquals(expectedResponseList.size(), actualResponseList.stream().toList().size());
    }

    @Test
    void update() {
        User updateUser = getNewUser();
        ResponseUserDto expectedResponse = getResponseNewUserDto();

        when(userRepository.findById(1L)).thenReturn(Optional.of(getUserById()));
        User saveUser = userRepository.save(updateUser);
        when(userMapper.mapUserToDto(saveUser)).thenReturn(expectedResponse);
        ResponseUserDto actualResponse = userService.update(1L, getRequestNewUserDto());

        assertEquals(expectedResponse.getFirstName(), actualResponse.getFirstName());
        assertEquals(expectedResponse.getLastName(), actualResponse.getLastName());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    void findByFirstName() {

    }

    @Test
    void delete() {
        userService.delete(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}