package com.stackroute.JPAOneToOneMapping.service;

import com.stackroute.JPAOneToOneMapping.exception.UserAlreadyExistsException;
import com.stackroute.JPAOneToOneMapping.exception.UserNotFoundException;
import com.stackroute.JPAOneToOneMapping.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistsException;

    User getUser(Long id) throws UserNotFoundException;

    User updateUser(User user) throws UserNotFoundException;

    List<User> getAllUsers();
}
