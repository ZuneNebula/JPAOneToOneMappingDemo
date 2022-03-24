package com.stackroute.JPAOneToOneMapping.controller;

import com.stackroute.JPAOneToOneMapping.exception.UserAlreadyExistsException;
import com.stackroute.JPAOneToOneMapping.exception.UserNotFoundException;
import com.stackroute.JPAOneToOneMapping.model.User;
import com.stackroute.JPAOneToOneMapping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;
    private ResponseEntity responseEntity;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    private ResponseEntity saveUser(@RequestBody User user) throws UserAlreadyExistsException{
       try{
           User user1 = userService.saveUser(user);
           responseEntity = new ResponseEntity(user1, HttpStatus.CREATED);
       }
       catch (UserAlreadyExistsException e){
           throw new UserAlreadyExistsException("user already exists");
       }
       catch (Exception ex){
           responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
       return responseEntity;
    }

    @GetMapping("/{id}")
    private ResponseEntity getUser(@PathVariable Long id) throws UserNotFoundException{
        try{
            User user1 = userService.getUser(id);
            responseEntity = new ResponseEntity(user1, HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            throw new UserNotFoundException("user not found");
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/")
    private ResponseEntity updateUser(@RequestBody User user) throws UserNotFoundException{
        try{
            User user1 = userService.updateUser(user);
            responseEntity = new ResponseEntity(user1, HttpStatus.CREATED);
        }
        catch (UserNotFoundException e){
            throw new UserNotFoundException("user not found");
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/all")
    private ResponseEntity getAllUsers(){
        try{
            List<User> userList = userService.getAllUsers();
            responseEntity = new ResponseEntity(userList, HttpStatus.OK);
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

}
