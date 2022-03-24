package com.stackroute.JPAOneToOneMapping.service;

import com.stackroute.JPAOneToOneMapping.exception.UserAlreadyExistsException;
import com.stackroute.JPAOneToOneMapping.exception.UserNotFoundException;
import com.stackroute.JPAOneToOneMapping.model.User;
import com.stackroute.JPAOneToOneMapping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(user.getId()==null){
            return userRepository.save(user);
        }
        if(userRepository.findById(user.getId()).isPresent()){
            throw new UserAlreadyExistsException("user already exists");
        }
        else{
            return userRepository.save(user);
        }
    }

    @Override
    public User getUser(Long id) throws UserNotFoundException {
        if(!userRepository.findById(id).isPresent()){
            throw new UserNotFoundException("user not found");
        }
        else{
            return userRepository.findById(id).get();
        }
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        if(!userRepository.findById(user.getId()).isPresent()){
            throw new UserNotFoundException("user not found");
        }
        else{
            return userRepository.save(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
