package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Address;
import com.example.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;
	
	public Address createAddress(Address address) {
		return repository.save(address);
	}
	
	public List<Address> getAllAddress() {
		return repository.findAll();
	}
}
