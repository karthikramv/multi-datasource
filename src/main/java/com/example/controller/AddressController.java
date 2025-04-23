package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Address;
import com.example.service.AddressService;

@RestController
@RequestMapping("/api")
public class AddressController {

	@Autowired
	private AddressService service;
	
	@PostMapping("/")
	public Address createAddress(@RequestBody Address address) {
		return service.createAddress(address);
	}
	
	@GetMapping("/")
	public List<Address> getAllAddress() {
		return service.getAllAddress();
	}
}
