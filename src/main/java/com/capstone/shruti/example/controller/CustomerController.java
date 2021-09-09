package com.capstone.shruti.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.shruti.example.entities.Customer;
import com.capstone.shruti.example.exception.ResourceNotFoundException;
import com.capstone.shruti.example.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/test")
	public String testing() {
		return "Woking";
	}
	
	@GetMapping("/Requesttest/{id}")
	public ResponseEntity<Customer> GetCustomerById(@PathVariable int id){
		Customer c1 = customerService.getCustomerById(id);
		return new ResponseEntity<Customer>(c1, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customer")
	public List <Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/customer/id{customerId}")
	public Customer getEmployeeById(@PathVariable int customerId) throws ResourceNotFoundException{
		
		Customer customer = customerService.getCustomerById(customerId);
		if(customer == null)
			throw new ResourceNotFoundException("Not Exist");
		return customer;
	}
	
	@DeleteMapping("/customer/{cid}")
	private void deleteCustomer(@PathVariable int customerId)
	{
		customerService.delete(customerId);
	}
	
	@DeleteMapping("/customer")
	private void deleteCustomer()
	{
		customerService.deleteAll();
	}
	
	@PostMapping("/customer")
	public ResponseEntity<Integer> saveCustomer(@RequestBody Customer customer) {
		customerService.saveOrUpdate(customer);
		int c = customer.getCustomerId();
		return new ResponseEntity<Integer>(c, HttpStatus.CREATED);
	}
	
	@PutMapping("/customer")
	private Customer update(@RequestBody Customer customer) {
		customerService.saveOrUpdate(customer);
		return customer;
	}
	
	@GetMapping("/customer/name/{name}")
	private List <Customer> findBy(@PathVariable String name){
		return customerService.findBy(name);
	}
}
