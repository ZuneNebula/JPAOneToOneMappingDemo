package com.stackroute.JPAOneToOneMapping.controller;

import com.stackroute.JPAOneToOneMapping.exception.AddressAlreadyExistsException;
import com.stackroute.JPAOneToOneMapping.exception.AddressNotFoundException;
import com.stackroute.JPAOneToOneMapping.model.Address;
import com.stackroute.JPAOneToOneMapping.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {
    private AddressService addressService;
    private ResponseEntity responseEntity;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/")
    private ResponseEntity saveAddress(@RequestBody Address address) throws AddressAlreadyExistsException{
        try{
            Address address1 = addressService.saveAddress(address);
            responseEntity = new ResponseEntity(address1, HttpStatus.CREATED);
        }
        catch (AddressAlreadyExistsException e){
            throw new AddressAlreadyExistsException("address already exists");
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/{id}")
    private ResponseEntity getAddress(@PathVariable Long id) throws AddressNotFoundException{
        try{
            Address address = addressService.getAddress(id);
            responseEntity = new ResponseEntity(address, HttpStatus.OK);
        }
        catch (AddressNotFoundException e){
            throw new AddressNotFoundException("address not found");
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/")
    private ResponseEntity updateAddress(@RequestBody Address address) throws AddressNotFoundException{
        try{
            Address address1 = addressService.updateAddress(address);
            responseEntity = new ResponseEntity(address1, HttpStatus.CREATED);
        }
        catch (AddressNotFoundException e){
            throw new AddressNotFoundException("address not found");
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/all")
    private ResponseEntity getAllAddresses(){
        try{
            List<Address> addressList = addressService.getAllAddresses();
            responseEntity = new ResponseEntity(addressList, HttpStatus.OK);
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
