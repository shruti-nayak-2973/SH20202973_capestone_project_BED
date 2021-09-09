package com.capstone.shruti.example.service;

import java.util.ArrayList;
import java.util.List;

import com.capstone.shruti.example.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.shruti.example.repository.CustomerRepo;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepo repo;
	
	public List <Customer> getAllCustomers(){
		List <Customer> customers = new ArrayList<Customer>();
		repo.findAll().forEach(customers1 -> customers.add(customers1));
		return customers;
	}
	
	public Customer getCustomerById(int id)
	{
		return repo.findById(id).get();
	}
	
	public void saveOrUpdate(Customer customer)
	{
		repo.save(customer);
	}
	
	public void delete(int id)
	{
		repo.deleteById(id);
	}
	
	public void deleteAll() {
		repo.deleteAll();
	}
	
	public void update(Customer customer, int customerId) {
		repo.save(customer);
	}
	
	public List<Customer> findBy(String name){
		return repo.findCustomerByName(name);
	}
}
