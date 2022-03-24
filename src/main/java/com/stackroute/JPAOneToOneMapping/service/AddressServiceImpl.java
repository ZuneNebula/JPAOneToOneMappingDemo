package com.stackroute.JPAOneToOneMapping.service;

import com.stackroute.JPAOneToOneMapping.exception.AddressAlreadyExistsException;
import com.stackroute.JPAOneToOneMapping.exception.AddressNotFoundException;
import com.stackroute.JPAOneToOneMapping.model.Address;
import com.stackroute.JPAOneToOneMapping.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{
    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address saveAddress(Address address) throws AddressAlreadyExistsException {
        if(address.getId()==null){
            return addressRepository.save(address);
        }
        if(addressRepository.findById(address.getId()).isPresent()){
            throw new AddressAlreadyExistsException("address already exists");
        }
        else{
            return addressRepository.save(address);
        }
    }

    @Override
    public Address getAddress(Long id) throws AddressNotFoundException {
        if(!addressRepository.findById(id).isPresent()){
            throw new AddressNotFoundException("address not found");
        }
        else{
            return addressRepository.findById(id).get();
        }
    }

    @Override
    public Address updateAddress(Address address) throws AddressNotFoundException {
        if(!addressRepository.findById(address.getId()).isPresent()){
            throw new AddressNotFoundException("address not found");
        }
        else{
            return addressRepository.save(address);
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }
}
