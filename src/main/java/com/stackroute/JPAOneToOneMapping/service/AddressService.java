package com.stackroute.JPAOneToOneMapping.service;

import com.stackroute.JPAOneToOneMapping.exception.AddressAlreadyExistsException;
import com.stackroute.JPAOneToOneMapping.exception.AddressNotFoundException;
import com.stackroute.JPAOneToOneMapping.model.Address;

import java.util.List;

public interface AddressService {

    Address saveAddress(Address address) throws AddressAlreadyExistsException;

    Address getAddress(Long id) throws AddressNotFoundException;

    Address updateAddress(Address address) throws AddressNotFoundException;

    List<Address> getAllAddresses();
}
